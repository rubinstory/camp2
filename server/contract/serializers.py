from rest_framework import serializers
from .models import Contract
from agency.serializers import *
from accounts.serializers import *

class ContractSerializer(serializers.ModelSerializer):
    # influencer = InfluencerSerializer()
    # user = UserSerializer()

    signature = serializers.SerializerMethodField('get_image_url')

    class Meta:
        model = Contract
        fields = '__all__'

    def get_image_url(self, obj):
        return obj.signature.url

