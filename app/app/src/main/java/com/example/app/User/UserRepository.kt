package com.example.app.User

import com.example.app.RetrofitInstance

class UserRepository {
    fun getUsers() = RetrofitInstance.api.get_users()

    suspend fun getUserById(id: Int) = RetrofitInstance.api.get_user_by_pk(id)
}