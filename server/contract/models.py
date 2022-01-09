from django.db import models
from agency.models import *
from accounts.models import *

# Create your models here.
class Contract(models.Model):
    signature = models.ImageField(blank = True, upload_to = "" )
    influencer = models.ForeignKey(Influencer, null = True, related_name = 'contract', on_delete = models.CASCADE)
    user = models.ForeignKey(User, null = True, related_name = 'contract', on_delete = models.CASCADE)

