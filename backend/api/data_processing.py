from django.db import connection
import pandas as pd
from sklearn.preprocessing import MultiLabelBinarizer
from sklearn.decomposition import PCA
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.datasets import dump_svmlight_file
from sklearn.cluster import KMeans

from .util import *
import pickle

cursor = connection.cursor()


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


def processing(user_id):
    user_log = user_log_data()
    user_category = user_category_data()
    book = book_data()
    review = review_data()

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
    mlb = MultiLabelBinarizer()
    user_cate[user_cate.columns[1:]] = mlb.fit_transform(
        user_cate['user_category_id'])
    user_cate.drop('user_category_id', axis=1, inplace=True)

    que = f'user_id=={user_id}'

    if(len(user_log.query(que)) != 0 and len(review.query(que)) != 0):
        data1 = pd.merge(user_log[["book_id", "user_id"]],
                         review, how="left", on=["book_id", "user_id"])
        data1['content'].fillna('', inplace=True)
        data1['content'] = data1['content'].str.replace(
            pat=r'[^A-Za-z0-9가-힣 ]', repl=r'', regex=True).str.strip()
        data1['rank'].fillna(int(data1['rank'].mean()), inplace=True)
        data1 = pd.merge(data1, book, on="book_id")

        user_see_cate = data1.groupby(['user_id', 'category_id'])[
            'rank'].mean().unstack().fillna(0)
        user_cate = pd.concat([user_cate, user_see_cate], axis=1).fillna(0)

    if(len(review.query(que)) != 0):
        # 리뷰 데이터 tf idf
        tfidf = TfidfVectorizer(stop_words='english', max_features=300)
        tfidf_matrix = tfidf.fit_transform(data1['content'])
        tfidf_data = pd.DataFrame(tfidf_matrix.toarray(),
                                  index=data1['user_id']).reset_index()
        tfidf_g_data = tfidf_data.groupby('user_id').mean()
        user_cate = pd.concat([user_cate, tfidf_g_data], axis=1).fillna(0)
    # 차원 축소 및 클러스터링

    pca = PCA(n_components=15)
    pca.fit(user_cate)
    pca_samples = pca.transform(user_cate)
    ps = pd.DataFrame(pca_samples)
    kmeans = KMeans(n_clusters=26, random_state=0)
    kmeans.fit(ps)

    pred = kmeans.predict(ps.loc[int(user_id), :].to_frame().T)
    user_cate['cluster'] = kmeans.labels_
    user_cate_cluster = user_cate[user_cate['cluster'] == pred[0]].drop(
        'cluster', axis=1)

    user_log_my = user_log[user_log['user_id'].isin(user_cate_cluster.index)]
    user_list = user_log_my['user_id'].unique()

    user_my_re = pd.merge(user_log_my[["book_id", "user_id"]],
                          review, how="left", on=["book_id", "user_id"])
    user_my_re_g = user_my_re.groupby(["book_id", "user_id"])[
        "rank"].mean().reset_index()
    user_my_re_g = pd.merge(user_my_re_g, user_cate, on="user_id")

    user_dump = pd.get_dummies(user_my_re_g['user_id'], prefix='u')
    book_dump = pd.get_dummies(user_my_re_g['book_id'], prefix='b')
    all_data = pd.concat([user_dump, book_dump,
                          user_my_re_g.drop(['book_id', 'user_id'], axis=1)], axis=1)

    y = all_data['rank']
    X = all_data.drop("rank", axis=1)

    train_path = os.path.join(
        get_project_root_path(), "backend", "static", "data", f'trainfm{user_id}.txt')
    test_path = os.path.join(
        get_project_root_path(), "backend", "static", "data", f'testfm{user_id}.txt')
    item_path = os.path.join(
        get_project_root_path(), "backend", "static", "item", f'book{user_id}.pkl')

    dump_svmlight_file(X.values, y.values, train_path)

    my_test = pd.concat([user_dump, book_dump,
                         user_my_re_g.drop(['user_id', 'rank'], axis=1)], axis=1)

    if int(user_id) in user_list:
        my_test = my_test[my_test["u_"+user_id] != 1].reset_index(drop=True)

    my_test.drop_duplicates("book_id", inplace=True)

    book_list = my_test['book_id'].values.tolist()
    pickle.dump(book_list, open(item_path, 'wb'))

    my_test.drop("book_id", axis=1, inplace=True)

    u_col = my_test.columns[my_test.columns.to_series(
    ).str.contains("u_").fillna(False)].values

    repl = np.full(u_col.shape, 0)

    my_test[my_test.columns[my_test.columns.to_series(
    ).str.contains("u_").fillna(False)]] = repl
    if int(user_id) in user_list:
        my_test["u_"+user_id] = 1

    my_test["rank"] = 0
    dump_svmlight_file(my_test.drop("rank", axis=1).values,
                       my_test["rank"].values, test_path)
