package com.example.app

import com.example.app.Influencer.Influencer
import com.example.app.Authentication.Authentication
import com.example.app.Contract.Contract
import com.example.app.Register.Register
import com.example.app.Token.Token
import com.example.app.User.User
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import kotlin.collections.List
import retrofit2.Call
import retrofit2.http.*

interface DjangoAPI {

    // AUTHENTICATION URL QUERIES
    @POST("/users/login/")
    fun login(@Body authentication: Authentication): Call<Token>

    @FormUrlEncoded
    @POST("/api/logout/")
    fun logout(@Field("refresh") token: String): Call<Token>

    @POST("/users/")
    fun register_new_user(@Body newUser: Register): Call<Token>

    @FormUrlEncoded
    @POST("/users/token/verify/")
    fun verify_access_token(@Field("token") token: String ): Call<Token>

    // INFLUENCER REQUEST QUERIES
    @POST("/agency/Influencers/")
    fun post_influencer(@Body influencer: Influencer): Call<Influencer>

    @GET("/agency/Influencers/")
    fun get_influencers(): Call<List<Influencer>>

    @GET("/agency/Influencers/{id}")
    fun get_influencer_by_pk(@Path("id") id:Int): Call<Influencer>

    @GET("/accounts/")
    fun get_users(): Call<List<User>>

    @GET("/accounts/users/{id}")
    fun get_user_by_pk(@Path("id") id: Int): Call<User>

    @Multipart
    @POST ("/contract/Contracts/")
    fun make_contract (
        @Part signatrue: MultipartBody.Part,
        @Part ("influencer_id") influencer_id: Int,
        @Part ("user_id") user_id: Int): Call<Contract>

    @GET("/contract/Contracts")
    fun get_contracts(): Call<List<Contract>>


}