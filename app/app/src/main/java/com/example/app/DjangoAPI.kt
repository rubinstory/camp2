package com.example.app

import java.util.List
import retrofit2.Call
import retrofit2.http.*

interface DjangoAPI {
    @POST("/Influencers/")
    fun post_influencer(@Body influencer: InfluencerItem): Call<InfluencerItem>

    @GET("/Influencers/")
    fun get_influencers(): Call<List<InfluencerItem>>

    @GET("/agency/Influencers/{id}")
    fun get_influencer_by_pk(@Header("Authorization") token: String, @Path("id") id:Int): Call<InfluencerItem>
}