package com.example.app

import com.example.app.Token.TokenUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val BASE_URL:String = "http://192.249.18.201:80"
    var ACCESS_TOKEN: String = "123"
    var REFRESH_TOKEN: String = ""
    var TOKENUSERID: Int = -1

    val clientBuilder = OkHttpClient.Builder()
    val loggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    val api: DjangoAPI by lazy {
        retrofit.create(DjangoAPI::class.java)
    }
}