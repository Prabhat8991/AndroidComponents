package com.example.androidcomponentsreferenceapp.components.room.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface VideoRetrofitService {
    @GET("devbytes.json")
    fun getPlayList(): Deferred<NetworkVideoContainer>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

object Network {
    private val retrofit = Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://devbytes.udacity.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    val videoService = retrofit.create(VideoRetrofitService::class.java)
}