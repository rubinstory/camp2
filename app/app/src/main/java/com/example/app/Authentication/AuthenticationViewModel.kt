package com.example.app.Authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.MainActivity
import com.example.app.RetrofitInstance
import com.example.app.Token.Token
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HTTP

class AuthenticationViewModel (private val repository: AuthenticationRepository): ViewModel() {

    var HTTP_STATUS: MutableLiveData<Int> = MutableLiveData<Int>()
    var authenticationRepository: AuthenticationRepository = AuthenticationRepository()

    fun loginWithAccount(authentication: Authentication) {
        viewModelScope.launch {
            authenticationRepository.login(authentication).enqueue(object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.isSuccessful) {
                        val token = response.body()!!
                        RetrofitInstance.ACCESS_TOKEN = token.access_token
                        RetrofitInstance.REFRESH_TOKEN = token.refresh_token
                        HTTP_STATUS.postValue(response.code())
                    }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                }
            })
        }
    }

    fun logout() {
        viewModelScope.launch {
            authenticationRepository.logout().enqueue(object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    RetrofitInstance.ACCESS_TOKEN = ""
                    RetrofitInstance.REFRESH_TOKEN = ""
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {

                }

            })
        }
    }

    fun authenticateToken() {
        viewModelScope.launch {
            authenticationRepository.authenticateToken().enqueue(object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    HTTP_STATUS.postValue(response.code())
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    HTTP_STATUS.postValue(-1)
                    Log.e("LOGIN", t.message.toString())
                }

            })
        }
    }
}
