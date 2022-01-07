from django.conf.urls import url, include
from django.contrib import admin
from rest_framework import routers
from accounts.views import UserCreate
from rest_framework import viewsets

router = routers.DefaultRouter()
router.register('usercreate', UserCreate)

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^', include(router.urls)),
]