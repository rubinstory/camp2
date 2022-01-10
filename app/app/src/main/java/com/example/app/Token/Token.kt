package com.example.app.Token

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("access_token") var access_token: String = "",
    @SerializedName("refresh_token") var refresh_token: String = "",
    @SerializedName("pk") var user: TokenUser,
)