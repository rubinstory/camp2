package com.example.app

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

//
//class InfluencerItem {
//    @SerializedName("first_name") var first_name: String? = null
//    @SerializedName("last_name") var last_name: String? = null
//    @SerializedName("age") var age: Int? = null
//    @SerializedName("height") var height: Int? = null
//    @SerializedName("weight") var weight: Int? = null
//    @SerializedName("country") var country: String? = null
//    @SerializedName("description") var description: String? = null
//
//    var full_name: String = ""
//        get() =  last_name + " " + first_name
//}

data class Influencer (
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("country") val country: String,
    @SerializedName("description") val description: String,

) {
    fun getFullName(): String = last_name + " " + first_name
}