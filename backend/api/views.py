from api import models, serializers
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response
from . import bookrecommend

# class SmallPagination(PageNumberPagination):
#     page_size = 10
#     page_size_query_param = "page_size"
#     max_page_size = 50


# class StoreViewSet(viewsets.ModelViewSet):
#     serializer_class = serializers.StoreSerializer
#     pagination_class = SmallPagination

#     def get_queryset(self):
#         name = self.request.query_params.get("name", "")
#         queryset = (
#             models.Store.objects.all().filter(store_name__contains=name).order_by("id")
#         )
#         return queryset

        
@api_view(['GET'])
def best(request):
    print("hi")
    return Response(bookrecommend.best())

@api_view(['GET'])
def recommend(request,user_id):
    return Response(bookrecommend.recommend(user_id))