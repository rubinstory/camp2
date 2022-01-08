package com.example.app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.List

class InfluencerRepository {

    suspend fun getInfluncers(id: Int): Call<List<Influencer>> {
        return RetrofitInstance.api.get_influencers()
    }

    fun getInfluencerById(id: Int) = RetrofitInstance.api.get_influencer_by_pk(id)
}