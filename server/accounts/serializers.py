from rest_framework import serializers
from .models import *
from contract.models import *
from contract.serializers import *


class UserSerializer(serializers.ModelSerializer):
    # image = serializers.ImageField(use_url = True)

    contract = ContractSerializer(many=True)
    
    def create(self, validated_data):
        user = User.objects.create_user(
            email =validated_data['email'],
            username =validated_data['username'],
            password =validated_data['password'],
            profile_image = validated_data['profile_image']
        )
        return user


    class Meta:
        model = User
        fields = ['id','is_admin',  'username', 'email', 'password', 'profile_image', 'contract']
        
        
            