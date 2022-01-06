from django.conf.urls import url, include
from django.contrib import admin
from rest_framework import routers
from agency.views import InfluencerViewSet

router = routers.DefaultRouter()
router.register('Influencers', InfluencerViewSet)

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^', include(router.urls)),
]