package com.example.app


/*
class Influencer(models.Model):
    first_name = models.CharField(max_length = 50)
    last_name = models.CharField(max_length = 50)
    age = models.IntegerField()
    height = models.IntegerField()
    weight = models.IntegerField()
    country = models.CharField(max_length = 100)
    description = models.TextField()
    producer = models.ForeignKey(Producer, on_delete=models.CASCADE, null = True)
 */

class InfluencerItem {
    var first_name: String? = null
    var last_name: String? = null
    var age: Int? = null
    var height: Int? = null
    var weight: Int? = null
    var country: String? = null
    var description: String? = null

    var full_name: String = ""
    get() =  last_name + " " + first_name
}