from django.shortcuts import render
from .models import Contract
from rest_framework import viewsets
from .serializers import ContractSerializer

# Create your views here.

class ContractViewSet(viewsets.ModelViewSet):
    queryset = Contract.objects.all()
    serializer_class = ContractSerializer
    