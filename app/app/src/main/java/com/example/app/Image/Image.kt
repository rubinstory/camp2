package com.example.app.Image

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("image") val url: String,
    @SerializedName("influencer") var influencer: Int
)