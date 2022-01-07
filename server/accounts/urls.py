from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from accounts.views import UserCreate

router = routers.DefaultRouter()
router.register('usercreate', UserCreate)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)), 
]