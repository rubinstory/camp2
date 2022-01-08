package com.example.app

import com.example.app.Influencer.Influencer
import com.example.app.Authentication.Authentication
import com.example.app.Register.Register
import com.example.app.Token.Token
import com.google.gson.annotations.SerializedName
import java.util.List
import retrofit2.Call
import retrofit2.http.*

interface DjangoAPI {

    // AUTHENTICATION URL QUERIES
    @POST("/users/login/")
    fun login(@Body authentication: Authentication): Call<Token>

    @POST("/users/logout/")
    fun logout(): Call<Token>

    @POST("/users/")
    fun register_new_user(@Body newUser: Register): Call<Token>

    @FormUrlEncoded
    @POST("/users/token/verify/")
    fun verify_access_token(@Field("token") token: String): Call<Token>

    // INFLUENCER REQUEST QUERIES
    @POST("/agency/Influencers/")
    fun post_influencer(@Body influencer: Influencer): Call<Influencer>

    @GET("/agency/Influencers/")
    fun get_influencers(): Call<List<Influencer>>

    @GET("/agency/Influencers/{id}")
    fun get_influencer_by_pk(@Path("id") id:Int): Call<Influencer>
}