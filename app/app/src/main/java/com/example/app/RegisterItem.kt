package com.example.app

import com.google.gson.annotations.SerializedName


//class InfluencerItem {
//    var first_name: String? = null
//    var last_name: String? = null
//    var age: Int? = null
//    var height: Int? = null
//    var weight: Int? = null
//    var country: String? = null
//    var description: String? = null
//
//    var full_name: String = ""
//        get() =  last_name + " " + first_name
//}

class RegisterItem {
    @SerializedName("username") var username: String? = null
    @SerializedName("email") var email: String? = null
    @SerializedName("password1") var password1: String? = null
    @SerializedName("password2") var password2: String? = null
}