from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from agency.views import *
from agency import views
from rest_framework import viewsets

router = routers.DefaultRouter()
router.register('Influencers', InfluencerViewSet)
# router.register('Images', ImagePortfolioViewSet)
# router.register('Videos', VideoPortfolioViewSet)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)),
    # path('<int:pk>/', views.get_image_by_influencer_id),
]