package com.example.app.Login

import com.example.app.Register.Register
import com.example.app.RetrofitInstance

class LoginRepository {
    suspend fun login(login: Login) = RetrofitInstance.api.login(login)
}