from rest_framework import serializers
from .models import Influencer, Producer

class InfluencerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Influencer
        fields = ('id', 'first_name', 'last_name', 'producer', 'age', 'height', 'weight', 'country', 'description')

class ProducerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producer
        fields = ('id', 'first_name', 'last_name', 'age')

