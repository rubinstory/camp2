package com.example.app.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthenticationViewModelFactory (private val repository: AuthenticationRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository) as T
    }
}