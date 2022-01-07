from rest_framework import serializers
from .models import *


class UserSerializer(serializers.ModelSerializer):
    def create(self, validated_data):
        user = User.objects.create_user(
            email =validated_data['email'],
            username =validated_data['username'],
            password =validated_data['password']
        )
        return user

    class Meta:
        model = User
        fields = ['username', 'email', 'password']

    