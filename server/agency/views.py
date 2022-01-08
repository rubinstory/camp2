from django.shortcuts import render
from rest_framework import viewsets
from .serializers import *
from .models import *
from rest_framework.decorators import api_view
from rest_framework.response import Response


# Create your views here.

class InfluencerViewSet(viewsets.ModelViewSet):
    queryset = Influencer.objects.all()
    serializer_class = InfluencerSerializer




# @api_view(["GET"])
# def get_image_by_influencer_id(request, pk):
#     try:
#         influencer = Influencer.objects.get(pk = pk)

#     except Influencer.DoesNotExist:
#         return Response(data={"msg": "No influencer"}, status=status.HTTP_404_NOT_FOUND)

#     else:
#         if request.method == "GET":
#             images = Image_Portfolio.objects.filter(influencer=influencer)
#             serializer = ImagePortfolioSerializer(images, many = True)
#             return Response(serializer.data)