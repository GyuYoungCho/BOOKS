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

from .FieldAwareFMRecommender import *


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
    get_project_root_path()
    resp = download(url, method='GET')
    result = resp.json()
    return result['item']


def fit(user_id):
    data = processing(user_id)
    ffm_data_path = os.path.join(
        get_project_root_path(), "resources", "ffm_data")
    model = FieldAwareFMRecommender(data, model_type="fm",
                                    train_svm_file_path=os.path.join(
                                        ffm_data_path, "train.txt"),
                                    valid_svm_file_path=os.path.join(
                                        ffm_data_path, "test.txt"),
                                    approximate_recommender=best_model, ICM_train=ICM_all, UCM_train=UCM_all,
                                    item_feature_fields=item_feature_fields, user_feature_fields=user_feature_fields,
                                    max_items_to_predict=20)

    model.fit(latent_factors=100, learning_rate=0.01,
              epochs=1000, regularization=1e-6, stop_window=5)

    return "success"


def recommend(user_id):

    return ''
