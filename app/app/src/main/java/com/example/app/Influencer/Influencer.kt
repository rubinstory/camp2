package com.example.app.Influencer

import com.example.app.Contract.Contract
import com.example.app.Image.Video
import com.example.app.Portfolio.Image
import com.google.gson.annotations.SerializedName

data class Influencer (
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("country") val country: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val imageList: List<Image>,
    @SerializedName("video") val videoList: List<Video>,
    @SerializedName("contract") val contractList: List<Contract>

) {
    fun getFullName(): String = last_name + " " + first_name
}