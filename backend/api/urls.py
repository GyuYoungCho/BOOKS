from django.urls import path
from . import views

app_name = "views"

urlpatterns = [
    path('main/best', views.best),
    path('main/fit/<user_id>', views.fit),
    path('main/recommend/<user_id>', views.recommend),
]
