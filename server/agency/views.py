from django.shortcuts import render
from rest_framework import viewsets
from .serializers import InfluencerSerializer
from .models import *

# Create your views here.

class InfluencerViewSet(viewsets.ModelViewSet):
    queryset = Influencer.objects.all()
    serializer_class = InfluencerSerializer
    
# class ProducerViewSet(viewsets.ModelViewSet):
#     queryset = Producer.objects.all()
#     serializer_class = ProducerSerializer
