import requests
import time
from urllib.robotparser import RobotFileParser
from requests.compat import urlparse, urljoin
from requests.exceptions import HTTPError
from .bookapi.bookapi import bookapikey

from django.db import connection
from .models import Book
from .models import User
from .models import Category
from .models import Review
from .models import UserLog
from .models import UserCategory

headers = {'user-agent':"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"}

def canfetch(url, agent='*', path='/'):
    robot = RobotFileParser(urljoin(url, '/robots.txt'))
    robot.read()
    return robot.can_fetch(agent, urlparse(url)[2])
    
def download(url, params={}, headers={}, method='GET', limit=3):
    method = method.upper()
    
    try:
        resp = requests.request(method, url,
                params=params if method=='GET' else {},
                data=params if method=='POST' else {},
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
    url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey={api}&QueryType=Bestseller&MaxResults=100&start=1&SearchTarget=Book&output=js&Version=20131101".format(api=bookapikey())
    resp = download(url,method= 'GET')
    result = resp.json()
    return result['item']

def recommend(user_id):
    return ''