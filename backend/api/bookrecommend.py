import requests
import time
from urllib.robotparser import RobotFileParser
from requests.compat import urlparse, urljoin
from requests.exceptions import HTTPError
from .bookapi.bookapi import bookapikey


from django.db import connection
from .models import *

import pandas as pd
import xlearn as xl

from sklearn.decomposition import PCA
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cluster import KMeans


headers = {
    'user-agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"}
cursor = connection.cursor()


def canfetch(url, agent='*', path='/'):
    robot = RobotFileParser(urljoin(url, '/robots.txt'))
    robot.read()
    return robot.can_fetch(agent, urlparse(url)[2])


def download(url, params={}, headers={}, method='GET', limit=3):
    method = method.upper()

    try:
        resp = requests.request(method, url,
                                params=params if method == 'GET' else {},
                                data=params if method == 'POST' else {},
                                headers=headers)
        resp.raise_for_status()
    except HTTPError as e:
        if limit > 0 and e.response.status_code >= 500:
            print(limit)
            time.sleep(300)
            resp = download(url, params, headers, method, limit-1)
        else:
            print('[{}] '.format(e.response.status_code) + url)
            print(e.response.status_code)
            print(e.response.reason)
            print(e.response.headers)
    return resp


def best():
    url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey={api}&QueryType=Bestseller&MaxResults=100&start=1&SearchTarget=Book&output=js&Version=20131101".format(
        api=bookapikey())
    resp = download(url, method='GET')
    result = resp.json()
    return result['item']


def user_log_data():
    cursor.execute("SELECT * FROM user_log")

    result = []
    for c in cursor:
        res = dict()
        res['reg_time'] = c[1]
        res['user_id'] = c[2]
        res['book_id'] = c[3]
        result.append(res)
    return pd.DataFrame(result).sort_values(by='reg_time', ascending=False).drop_duplicates(['user_id', 'book_id'], keep='first').reset_index(drop=True)


def book_data():
    cursor.execute("SELECT * FROM book")

    result = []
    for c in cursor:
        res = dict()
        res['book_id'] = c[0]
        res['category_id'] = c[4]
        result.append(res)

    return pd.DataFrame(result)


def user_category_data():
    cursor.execute("SELECT * FROM user_category")

    result = []
    for c in cursor:
        res = dict()
        res['user_id'] = c[1]
        res['user_category_id'] = c[2]
        result.append(res)

    return pd.DataFrame(result)


def review_data():
    cursor.execute("SELECT * FROM review")

    result = []
    for c in cursor:
        res = dict()
        res['book_id'] = c[5]
        res['user_id'] = c[4]
        res['rank'] = c[1]
        res['reg_time'] = c[3]
        res['content'] = c[2]
        result.append(res)

    return pd.DataFrame(result).sort_values(by='reg_time', ascending=False).drop_duplicates(['user_id', 'book_id'], keep='first').reset_index(drop=True)


def cate_coding(x):
    for cate in x['user_category_id']:
        x['c_'+str(cate)] = 1
    return x


def recommend(user_id):

    # 데이터 불러오기
    user_log = user_log_data()
    user_category = user_category_data()
    book = book_data()
    review = review_data()

    # 리뷰 데이터와 로그 데이터 합치고 전처리
    data1 = pd.merge(user_log[["book_id", "user_id"]],
                     review, how="left", on=["book_id", "user_id"])
    data1['content'].fillna('', inplace=True)
    data1['content'] = data1['content'].str.replace(
        pat=r'[^A-Za-z0-9가-힣 ]', repl=r'', regex=True).str.strip()
    data1['rank'].fillna(int(data1['rank'].mean()), inplace=True)
    data1 = pd.merge(data1, book, on="book_id")

    # user의 관심 카테고리 user_id별 리스트화
    user_category['user_category_id'] = user_category['user_category_id'].astype(
        str)
    user_cate = user_category.groupby(
        'user_id').agg(lambda x: ' '.join(set(x)))
    user_cate['user_category_id'] = user_cate['user_category_id'].str.split(
        ' ')

    cate_list = book['category_id'].unique()
    for cate in cate_list:
        user_cate['c_' + str(cate)] = 0

    # user의 관심 카테고리 및 로그에 쌓인 카테고리 평점을 평균낸어 합체
    user_cate = user_cate.apply(cate_coding, axis=1)
    user_pre_cate = user_cate.drop('user_category_id', axis=1)
    user_see_cate = data1.pivot_table(
        values='rank', index='user_id', columns='category_id').fillna(0)
    user_cate = pd.concat([user_pre_cate, user_see_cate], axis=1).fillna(0)

    # 리뷰 데이터 tf idf
    tfidf = TfidfVectorizer(stop_words='english', max_features=300)
    tfidf_matrix = tfidf.fit_transform(data1['content'])
    tfidf_data = pd.DataFrame(tfidf_matrix.toarray(),
                              index=data1['user_id']).reset_index()
    tfidf_g_data = tfidf_data.groupby('user_id').mean()
    user_cate_tf = pd.concat([user_cate, tfidf_g_data], axis=1).fillna(0)

    # 차원 축소 및 클러스터링

    pca = PCA(n_components=3)
    pca.fit(user_cate_tf)
    pca_samples = pca.transform(user_cate_tf)
    ps = pd.DataFrame(pca_samples)

    kmeans = KMeans(n_clusters=4)
    kmeans.fit(ps)

    return ''
