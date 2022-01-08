from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from contract.views import ContractViewSet
from rest_framework import viewsets

router = routers.DefaultRouter()
router.register('Contracts', ContractViewSet)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)),
]