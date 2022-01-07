from django.conf import settings
from django.shortcuts import render
from rest_framework import viewsets
from .serializers import UserSerializer
from .models import User
from dj_rest_auth.registration.views import SocialLoginView
from allauth.socialaccount.providers.kakao.views import KakaoOAuth2Adapter

# Create your views here.

class UserCreate(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    
# class KakaoLogin(SocialLoginView):
#     adapter_class = KakaoOAuth2Adapter
#     # client_class = OAuth2Client
#     # callback_uri = getattr(settings, 'KAKAO_CALLBACK_URI')
