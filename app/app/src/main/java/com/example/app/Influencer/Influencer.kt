package com.example.app.Influencer

import com.example.app.Item.Image
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class Influencer (
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("country") val country: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val imageList: List<Image>

) {
    fun getFullName(): String = last_name + " " + first_name
}