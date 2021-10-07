from api import models
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response
from . import bookrecommend


@api_view(['GET'])
def best(request):
    return Response(bookrecommend.best())


@api_view(['GET'])
def fit(request, user_id):
    return Response(bookrecommend.fit(user_id))


@api_view(['GET'])
def recommend(request, user_id):
    return Response(bookrecommend.recommend(user_id))
