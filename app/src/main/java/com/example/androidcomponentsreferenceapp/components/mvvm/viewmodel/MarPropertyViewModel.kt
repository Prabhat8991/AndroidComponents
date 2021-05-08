package com.example.androidcomponentsreferenceapp.components.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcomponentsreferenceapp.components.mvvm.model.MarsPropertyDetail
import com.example.androidcomponentsreferenceapp.components.mvvm.network.MarsApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MarPropertyViewModel(val repository: MarsApiRepository) : ViewModel() {

    private var _propertyList: MutableLiveData<List<MarsPropertyDetail>> = MutableLiveData<List<MarsPropertyDetail>>()

    val propertyList: LiveData<List<MarsPropertyDetail>>
        get() = _propertyList

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsListProperties()
    }

    private fun getMarsListProperties() {
        coroutineScope.launch {
            val deferredMarsPropertyDetailList = repository.getMarsPropertyList()
            try {
                val resultList = deferredMarsPropertyDetailList.await()
                _propertyList.value = resultList
            } catch (e: Exception) {
                // error
            }
        }
    }

}