from django.conf import settings
from django.shortcuts import render
from rest_framework import viewsets, permissions, status
from .serializers import UserSerializer
from .models import User
from rest_framework_simplejwt import authentication
from rest_framework.decorators import api_view
from rest_framework.response import Response

# Create your views here.

class UserCreate(viewsets.ModelViewSet):
    permissions_classes = [permissions.IsAuthenticated]
    authentication_classes = [authentication.JWTAuthentication]
    queryset = User.objects.all()
    serializer_class = UserSerializer


@api_view(["GET"])
def get_user_by_id(request, pk):
    try:
        user = User.objects.get(pk = pk)
    except User.DoesNotExist:
        return Response(data={"msg": "No such show"}, status=status.HTTP_404_NOT_FOUND)

    else:
        if request.method == "GET":
            s = UserSerializer(user, context={"request":request})
            return Response(s.data)