from django.db import models
from accounts.models import User

class Influencer(models.Model):
    first_name = models.CharField(max_length = 50)
    last_name = models.CharField(max_length = 50)
    age = models.IntegerField()
    height = models.IntegerField()
    weight = models.IntegerField()
    country = models.CharField(max_length = 100)
    description = models.TextField()
    producer = models.ForeignKey(User, null = True, on_delete = models.CASCADE)

    def __str__(self):
        return self.last_name + self.first_name + "/" + str(self.id)
