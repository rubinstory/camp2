package com.example.app.Influencer

import com.example.app.RetrofitInstance
import retrofit2.Call
import java.util.List

class InfluencerRepository {

    suspend fun getInfluncers(id: Int): Call<List<Influencer>> {
        return RetrofitInstance.api.get_influencers()
    }

    fun getInfluencerById(id: Int) = RetrofitInstance.api.get_influencer_by_pk(id)
}