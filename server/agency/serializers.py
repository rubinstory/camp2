from rest_framework import serializers
from .models import Influencer

class InfluencerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Influencer
        fields = ('id', 'first_name', 'last_name', 'age', 'height', 'weight', 'country', 'description')

