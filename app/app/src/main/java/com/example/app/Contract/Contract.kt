package com.example.app.Contract

import android.net.Uri
import com.example.app.User.User
import com.google.gson.annotations.SerializedName
import java.io.ByteArrayOutputStream

data class Contract(
    @SerializedName("id") val id: Int,
    @SerializedName("signature") val signature: String,
    @SerializedName("influencer") val influencer_id: Int,
    @SerializedName("user" ) val user_id: Int,
    @SerializedName("username") val username: String
)