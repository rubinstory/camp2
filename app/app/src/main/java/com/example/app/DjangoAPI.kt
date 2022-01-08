package com.example.app

import com.example.app.Influencer.Influencer
import com.example.app.Login.Login
import com.example.app.Register.Register
import com.example.app.Token.Token
import java.util.List
import retrofit2.Call
import retrofit2.http.*

interface DjangoAPI {
    @POST("/accounts/login")
    fun login(@Body login: Login): Call<Token>

    @POST("/agency/Influencers/")
    fun post_influencer(@Body influencer: Influencer): Call<Influencer>

    @GET("/agency/Influencers/")
    fun get_influencers(): Call<List<Influencer>>

    @GET("/agency/Influencers/{id}")
    fun get_influencer_by_pk(@Path("id") id:Int): Call<Influencer>

    @POST("/accounts/")
    fun register_new_user(@Body newUser: Register): Call<Token>
}