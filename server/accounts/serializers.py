from rest_framework import serializers
from .models import *


class UserSerializer(serializers.ModelSerializer):
    # image = serializers.ImageField(use_url = True)

    def create(self, validated_data):
        user = User.objects.create_user(
            email =validated_data['email'],
            username =validated_data['username'],
            password =validated_data['password'],
            # image = validated_data['image']
        )
        return user

    class Meta:
        model = User
        fields = ['username', 'email', 'password']
        
        
            