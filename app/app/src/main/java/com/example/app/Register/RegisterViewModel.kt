package com.example.app.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.RetrofitInstance
import com.example.app.Token.Token
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel (private val repository: RegisterRepository): ViewModel() {
    var registerRepository: RegisterRepository = RegisterRepository()

    fun registerNewUser(newUser: Register) {
        viewModelScope.launch {
            registerRepository.register(newUser).enqueue(object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.isSuccessful) {
                        var token: Token = response.body()!!
                        RetrofitInstance.ACCESS_TOKEN = token.access_token
                        RetrofitInstance.REFRESH_TOKEN = token.refresh_token
                        RetrofitInstance.USER_ID = token.user.id
                    }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {

                }

            })
        }
    }
}

