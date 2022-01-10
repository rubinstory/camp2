package com.example.app.User

import com.example.app.Contract.Contract
import com.example.app.Image.Video
import com.example.app.Portfolio.Image
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id") val id: Int,
    @SerializedName("is_admin") val is_admin: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("profile_image") val profile_image: String,
)