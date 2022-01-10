from rest_framework import serializers
from .models import Contract
from agency.serializers import *
from accounts.serializers import *

class ContractSerializer(serializers.ModelSerializer):
    # influencer = InfluencerSerializer()
    # user = UserSerializer()

    class Meta:
        model = Contract
        fields = '__all__'

    class get_image_url(self, obj):
        return obj.image.url

