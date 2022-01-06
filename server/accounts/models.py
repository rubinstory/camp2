from django.db import models
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager, UserManager

# Create your models here.

        
class UserManager(BaseUserManager):
    
    def create_user(self, email, name, nickname, password=None):
        if not email:
            raise ValueError('must have user email')

        if not nickname:
            raise ValueError('must have user nickname')

        if not name:
            raise ValueError('must have user name')

        user = self.model(
            email = self.normalize_email(email),
            name = name,
            nickname = nickname
        )
        user.set_password(password)
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):
    id = models.AutoField(primary_key=True)
    nickname = models.CharField(default='', max_length=100, null=False, blank =False)
    email = models.EmailField(default='', max_length=100, null =False, blank = False, unique = True)
    name = models.CharField(default='', max_length=100, null=False, blank = False)

    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)

    objects = UserManager()

    USERNAME_FIELD = 'nickname'

    REUQUIRED_FIELDS = ['name', 'email']

    def __str__(self):
        return self.nickname

