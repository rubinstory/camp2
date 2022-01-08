package com.example.app.Authentication

import com.example.app.RetrofitInstance

class AuthenticationRepository {
    suspend fun login(authentication: Authentication) = RetrofitInstance.api.login(authentication)
    suspend fun logout() = RetrofitInstance.api.logout(RetrofitInstance.REFRESH_TOKEN)
    suspend fun authenticateToken() = RetrofitInstance.api.verify_access_token(RetrofitInstance.ACCESS_TOKEN)
}