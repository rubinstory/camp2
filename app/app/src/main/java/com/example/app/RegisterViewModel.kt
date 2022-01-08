package com.example.app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel (private val repository: RegisterRepository): ViewModel() {
    var registerRepository: RegisterRepository = RegisterRepository()
    var registerInfo: MutableLiveData<Register> = MutableLiveData<Register>()

    fun registerNewUser(newUser: Register) {
        viewModelScope.launch {
            registerRepository.register(newUser).enqueue(object: Callback<Register>{
                override fun onResponse(call: Call<Register>, response: Response<Register>) {
                    if (response.isSuccessful) {
                        var token: Register = response.body()!!
                        RetrofitInstance.setAccessToken(token.access_token!!)
                        RetrofitInstance.setRefreshToken(token.refresh_token!!)
                    }
                }

                override fun onFailure(call: Call<Register>, t: Throwable) {

                }

            })
        }
    }
}

