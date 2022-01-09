package com.example.app.Image

import com.google.gson.annotations.SerializedName

data class Video (
    @SerializedName("video") val url: String,
    @SerializedName("influencer") var influencer: Int
)