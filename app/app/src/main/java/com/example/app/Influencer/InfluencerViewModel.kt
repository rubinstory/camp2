package com.example.app.Influencer

import kotlin.collections.List
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfluencerViewModel(private val repository: InfluencerRepository): ViewModel() {
    var influencerRepository: InfluencerRepository = InfluencerRepository()
    var influencerList: MutableLiveData<List<Influencer>> = MutableLiveData<List<Influencer>>()

    fun getInfluencers() {
        viewModelScope.launch {
           influencerRepository.getInfluencers().enqueue(object: Callback<List<Influencer>> {
               override fun onResponse(
                   call: Call<List<Influencer>>,
                   response: Response<List<Influencer>>
               ) {
                   if (response.isSuccessful) {
                       influencerList.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<List<Influencer>>, t: Throwable) {
                   Log.e("INFLUENCER", t.message.toString())
               }

           })
        }
    }


    fun getInfluencerById(id: Int) { // async
        viewModelScope.launch {
            influencerRepository.getInfluencerById(id).enqueue(object: Callback<Influencer> { // async
                override fun onResponse(call: Call<Influencer>, response: Response<Influencer>) {
                    if (response.isSuccessful) {
                        influencerList.postValue(listOf(response.body()!!))
                    }
                }

                override fun onFailure(call: Call<Influencer>, t: Throwable) {
                    Log.e("INFLUENCER REPOSITORY", t.message.toString())
                }
            })
        }
    }
}
