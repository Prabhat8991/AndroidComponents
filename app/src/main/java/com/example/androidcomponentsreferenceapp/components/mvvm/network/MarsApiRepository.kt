package com.example.androidcomponentsreferenceapp.components.mvvm.network

import com.example.androidcomponentsreferenceapp.components.mvvm.model.MarsPropertyDetail
import kotlinx.coroutines.Deferred

class MarsApiRepository(val apiService: MarsApiService) {

    fun getMarsPropertyList(): Deferred<List<MarsPropertyDetail>> = apiService.getMarsProperties()
}