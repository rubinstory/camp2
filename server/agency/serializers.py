from rest_framework import serializers
from .models import *
from accounts.serializers import *
from contract.serializers import *

    


class ImagePortfolioSerializer(serializers.ModelSerializer):

    class Meta:
        model = Image_Portfolio
        fields = '__all__'


class VideoPortfolioSerializer(serializers.ModelSerializer):

    class Meta:
        model = Video_Portfolio
        fields = '__all__'



class InfluencerSerializer(serializers.ModelSerializer):
    image = ImagePortfolioSerializer(many = True)
    video = VideoPortfolioSerializer(many = True)
    contract = ContractSerializer(many = True)

    class Meta:
        model = Influencer
        fields = ('first_name', 'last_name', 'age', 'height', 'weight', 'country', 'description', 'producer', 'image', 'video', 'contract',)