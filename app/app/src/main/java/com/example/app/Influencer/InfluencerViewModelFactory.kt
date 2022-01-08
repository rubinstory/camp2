package com.example.app.Influencer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InfluencerViewModelFactory(private val repository: InfluencerRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfluencerViewModel(repository) as T
    }
}