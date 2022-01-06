from django.db import models

# Create your models here.

# class Producer(models.Model):
#     first_name =  models.CharField(max_length = 50)
#     last_name = models.CharField(max_length = 50)
#     age = models.IntegerField()

#     def __str__(self):
#         return self.last_name+ self.first_name

class Influencer(models.Model):
    first_name = models.CharField(max_length = 50)
    last_name = models.CharField(max_length = 50)
    age = models.IntegerField()
    height = models.IntegerField()
    weight = models.IntegerField()
    country = models.CharField(max_length = 100)
    description = models.TextField()
    # producer = models.ForeignKey(Producer, on_delete=models.CASCADE, null = True)

    def __str__(self):
        return self.last_name + self.first_name


    


# class User(models.Model):
