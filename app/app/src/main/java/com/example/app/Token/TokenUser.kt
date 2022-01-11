package com.example.app.Token

import com.google.gson.annotations.SerializedName

data class TokenUser (
    @SerializedName("pk") var id: Int,
    @SerializedName("username") var username: String,
)
