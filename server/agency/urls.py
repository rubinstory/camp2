from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from agency.views import InfluencerViewSet
from rest_framework import viewsets

router = routers.DefaultRouter()
router.register('Influencers', InfluencerViewSet)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)),
]