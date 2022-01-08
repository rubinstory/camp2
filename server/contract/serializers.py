from rest_framework import serializers
from .models import Contract
from agency.serializers import InfluencerSerializer
from accounts.serializers import UserSerializer

class ContractSerializer(serializers.ModelSerializer):
    influencer = InfluencerSerializer()
    user = UserSerializer()

    class Meta:
        model = Contract
        fields = ('signature', 'influencer', 'user')

