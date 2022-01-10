package com.example.app.Contract

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream

data class ContractUploadItem (
    @SerializedName("signature") val signature: MultipartBody.Part,
    @SerializedName("influencer") val influencerId: Int,
    @SerializedName("user" ) val userId: Int
)