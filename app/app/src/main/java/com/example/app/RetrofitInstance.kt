package com.example.app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL:String = "http://192.249.18.201:80"
    var ACCESS_TOKEN: String = "123"
    var REFRESH_TOKEN: String = ""
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjQxNjQ5OTA0LCJpYXQiOjE2NDE2NDYzMDQsImp0aSI6IjcwMThlZDg5NGIyMzQ3NzA4MWU3N2FjY2U2Y2FmNmUyIiwidXNlcl9pZCI6MX0.t8Svh-YCDXmKUGntYbT-EJCC-kXRC0rReK6u5lVm9CQ

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: DjangoAPI by lazy {
        retrofit.create(DjangoAPI::class.java)
    }
}