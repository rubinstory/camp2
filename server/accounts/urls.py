from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from accounts.views import UserCreate
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

from accounts import views


urlpatterns = [
    path('admin/', admin.site.urls),
    path('', UserCreate.as_view({'get' : 'list'}), name = 'user_list'),
    path('<int:pk>/', views.get_user_by_id),
    path('token/', TokenObtainPairView.as_view(), name = 'token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name = 'token_refresh'),
    path('<str:name>', views.get_user_by_name, name='sdfsfs')
]