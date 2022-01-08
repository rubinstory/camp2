package com.example.app

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


    fun getInfluencerById(id: Int) {
        viewModelScope.launch {
            influencerRepository.getInfluencerById(id).enqueue(object: Callback<Influencer> {
                override fun onResponse(call: Call<Influencer>, response: Response<Influencer>) {
                    if (response.isSuccessful) {
                        var singleObject = mutableListOf<Influencer>()
                        singleObject.add(response.body()!!)
                        influencerList.postValue(singleObject)
                    }
                }

                override fun onFailure(call: Call<Influencer>, t: Throwable) {
                    Log.e("INFLUENCER REPOSITORY", t.message.toString())
                }
            })
        }
    }
}