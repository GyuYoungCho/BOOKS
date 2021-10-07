import os
import requests
import time
from urllib.robotparser import RobotFileParser
from requests.compat import urlparse, urljoin
from requests.exceptions import HTTPError
from .bookapi.bookapi import bookapikey


from django.db import connection
from .data_processing import *
from .util import *

import pandas as pd
import xlearn as xl


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


def apk(actual, predicted, k=10):
    if len(predicted) > k:
        predicted = predicted[:k]

    score = 0.0
    num_hits = 0.0

    for i, p in enumerate(predicted):
        if p in actual and p not in predicted[:i]:
            num_hits += 1.0
            score += num_hits / (i+1.0)

    if not actual:
        return 0.0

    return score / min(len(actual), k)


def mapk(actual, predicted, k=10):
    return np.mean([apk(a, p, k) for a, p in zip(actual, predicted)])


def best():
    url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey={api}&QueryType=Bestseller&MaxResults=100&start=1&SearchTarget=Book&output=js&Version=20131101".format(
        api=bookapikey())
    print(get_project_root_path())

    resp = download(url, method='GET')
    result = resp.json()
    return result['item']


def fit(user_id):
    processing(user_id)

    train_path = os.path.join(
        get_project_root_path(), "backend", "static", "data", f'trainfm{user_id}.txt')
    model_path = os.path.join(
        get_project_root_path(), "backend", "static", "model", f'modelfm{user_id}.out')

    params = {'epoch': 50, 'task': 'reg',
              'metric': 'rmse', 'k': 10, 'stop_window': 3}

    ffm = xl.create_ffm()

    ffm.setTrain(train_path)
    ffm.fit(params, model_path)

    return "success"


def recommend(user_id):
    test_path = os.path.join(
        get_project_root_path(), "backend", "static", "data", f'testfm{user_id}.txt')
    model_path = os.path.join(
        get_project_root_path(), "backend", "static", "model", f'modelfm{user_id}.out')
    output_path = os.path.join(
        get_project_root_path(), "backend", "static", "output", f'resultfm{user_id}.txt')
    item_path = os.path.join(
        get_project_root_path(), "backend", "static", "item", f'book{user_id}.pkl')

    ffm = xl.create_ffm()
    ffm.setTest(test_path)
    ffm.predict(model_path, output_path)

    preds = np.loadtxt(output_path)
    with open(item_path, 'rb') as f:
        book_list = pickle.load(f)

    df_ranked = pd.Series(preds, index=book_list)

    df_ranked.sort_values(ascending=False, inplace=True)
    k = len(df_ranked.index) if len(df_ranked.index) < 10 else 10
    recomendations_list = df_ranked.index.values[:k].tolist()

    cursor.execute(
        'SELECT * FROM book where book_id in {}'.format(tuple(recomendations_list)))

    result = []
    for c in cursor:
        res = dict()
        res['book_id'] = c[0]
        res['isbn'] = c[1]
        res['title'] = c[2]
        res['coverFilepath'] = c[3]
        res['category_id'] = c[4]
        result.append(res)

    return result
