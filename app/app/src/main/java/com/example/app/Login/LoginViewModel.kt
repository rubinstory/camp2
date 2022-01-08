package com.example.app.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.Register.Register
import com.example.app.Register.RegisterRepository
import com.example.app.RetrofitInstance
import com.example.app.Token.Token
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (private val repository: LoginRepository): ViewModel() {
    var loginRepository: LoginRepository = LoginRepository()


    fun loginWithAccount(login: Login) {
        viewModelScope.launch {
            loginRepository.login(login).enqueue(object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.isSuccessful) {
                        val token = response.body()!!
                        RetrofitInstance.ACCESS_TOKEN = token.access_token
                        RetrofitInstance.REFRESH_TOKEN = token.refresh_token
                    }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                }
            })
        }
    }
}
