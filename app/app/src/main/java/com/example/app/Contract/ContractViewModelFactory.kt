package com.example.app.Contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContractViewModelFactory(private val repository: ContractRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContractViewModel(repository) as T
    }
}