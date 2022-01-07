package com.example.app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class DjangoAPICall {

    private val BASE_URL:String = "http://192.168.0.29:8000"

    fun init() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        API = retrofit.create(DjangoAPI::class.java)
    }

    companion object {
        lateinit var API: DjangoAPI
        var token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjQxNTcwNjIwLCJqdGkiOiJlYmUwNjU5OWFjMWM0NTA1YjcxYmQyMjgzNWQ4N2QwOSIsInVzZXJfaWQiOjN9.QyvCW3KKrQTu21xWoumLuGL-_OeOkm-N8J-tKXzZbAA"
    }
}