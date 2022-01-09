from django.conf.urls import include
from django.urls import path
from django.contrib import admin
from rest_framework import routers
from contract.views import ContractViewSet
from rest_framework import viewsets
from contract import views

router = routers.DefaultRouter()
router.register('Contracts', ContractViewSet)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)),
    path('mine_block/', views.mine_block),
    path('get_chain/', views.get_chain),
    path('new_transaction/', views.new_transaction),
]   