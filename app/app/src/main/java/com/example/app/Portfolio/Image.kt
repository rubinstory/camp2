package com.example.app.Portfolio

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("image") val url: String,
    @SerializedName("influencer") var influencer: Int
)