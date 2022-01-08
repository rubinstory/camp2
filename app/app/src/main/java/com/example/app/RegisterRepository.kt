package com.example.app


import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.List

class RegisterRepository {
    suspend fun register(register: Register) = RetrofitInstance.api.register_new_user(register)
}