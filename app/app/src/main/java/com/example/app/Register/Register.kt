package com.example.app.Register

import com.google.gson.annotations.SerializedName

data class Register (
    @SerializedName("username") var username: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password1") var password1: String? = null,
    @SerializedName("password2") var password2: String? = null,
)