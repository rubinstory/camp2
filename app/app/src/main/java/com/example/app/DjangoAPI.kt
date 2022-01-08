package com.example.app

import java.util.List
import retrofit2.Call
import retrofit2.http.*

interface DjangoAPI {
    @POST("/Influencers/")
    fun post_influencer(@Body influencer: Influencer): Call<Influencer>

    @GET("/Influencers/")
    fun get_influencers(): Call<List<Influencer>>

    @GET("/Influencers/{id}")
    fun get_influencer_by_pk(@Path("id") id:Int): Call<Influencer>

    @POST("/accounts/")
    fun register_new_user(@Body newUser: Register): Call<Register>
}