from rest_framework import serializers
from .models import *


class UserSerializer(serializers.ModelSerializer):
    def create(self, validated_data):
        user = User.objects.create_user(
            email =validated_data['email'],
            nickname =validated_data['nickname'],
            # name =validated_data['name'],
            password =validated_data['password']
        )
        return user

    class Meta:
        model = User
        fields = ['nickname', 'email', 'password']