from django.db import models
from agency.models import Influencer
from accounts.models import User

# Create your models here.
class Contract(models.Model):
    
    signature = models.ImageField(blank = True, upload_to = "" )
    influencer = models.ForeignKey(Influencer, null = True, on_delete = models.CASCADE)
    user = models.ForeignKey(User, null = True, on_delete = models.CASCADE)

    def __str__(self):
        return self.last_name + self.first_name + "/" + str(self.id)
