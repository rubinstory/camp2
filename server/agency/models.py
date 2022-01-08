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

class Image_Portfolio(models.Model):
    influencer = models.ForeignKey(Influencer, related_name = 'image', on_delete=models.CASCADE)
    image = models.ImageField(upload_to="")

class Video_Portfolio(models.Model):
    influencer = models.ForeignKey(Influencer,  related_name = 'video', on_delete=models.CASCADE)
    video = models.FileField(upload_to="")

