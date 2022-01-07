# from django.conf.urls import url, include
from django.urls import path, include
from django.contrib import admin
from rest_framework import routers
from accounts.views import UserCreate
from rest_framework import viewsets

router = routers.DefaultRouter()
router.register('usercreate', UserCreate)

urlpatterns = [
    path(r'^admin/', admin.site.urls),
    path(r'^', include(router.urls)),
]