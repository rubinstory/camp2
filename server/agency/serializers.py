from rest_framework import serializers
from .models import Influencer
from accounts.serializers import UserSerializer

class InfluencerSerializer(serializers.ModelSerializer):
    producer = UserSerializer()

    class Meta:
        model = Influencer
        fields = '__all__'

