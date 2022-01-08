package com.example.app.Register


import com.example.app.RetrofitInstance

class RegisterRepository {
    suspend fun register(register: Register) = RetrofitInstance.api.register_new_user(register)
}