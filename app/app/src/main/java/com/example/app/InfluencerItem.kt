package com.example.app

class InfluencerItem {
    var first_name: String? = null
    var last_name: String? = null
    var age: Int? = null
    var height: Int? = null
    var weight: Int? = null
    var country: String? = null
    var description: String? = null

    var full_name: String = ""
    get() =  last_name + " " + first_name
}