package com.example.app

import java.util.List
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DjangoAPI {
    @POST("/Influencers/")
    fun post_influencer(@Body influencer: InfluencerItem): Call<InfluencerItem>

    @GET("/Influencers/")
    fun get_influencers(): Call<List<InfluencerItem>>

    @GET("/Influencers/{id}")
    fun get_influencer_by_pk(@Path("id") id:Int): Call<InfluencerItem>
}