package com.example.app

import com.example.app.Token.TokenUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL:String = "http://192.249.18.201:80"
    var ACCESS_TOKEN: String = "123"
    var REFRESH_TOKEN: String = ""
    var TOKENUSERID: Int = -1

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