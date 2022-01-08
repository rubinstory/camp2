from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from accounts.views import UserCreate
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from blockchainapp import views

urlpatterns = [
    # path('', views.BlockChainView.as_view(), name='blockchain'),
    path('mine_block/', views.mine_block),
    path('get_chain/', views.get_chain),
]