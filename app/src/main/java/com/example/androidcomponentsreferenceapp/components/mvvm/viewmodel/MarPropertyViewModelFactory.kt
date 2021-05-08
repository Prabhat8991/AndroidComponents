package com.example.androidcomponentsreferenceapp.components.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponentsreferenceapp.components.mvvm.network.MarsApiRepository

class MarPropertyViewModelFactory(private val repository: MarsApiRepository): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarPropertyViewModel::class.java)) {
            return MarPropertyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}