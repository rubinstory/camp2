package com.example.app.Contract

import com.example.app.Influencer.Influencer
import com.example.app.User.User
import com.google.gson.annotations.SerializedName

data class Contract (
    @SerializedName("id") val id: Int,
    @SerializedName("signature") val url: String,
    @SerializedName("influencer") var influencer: Influencer,
    @SerializedName("user" ) var user: User
)