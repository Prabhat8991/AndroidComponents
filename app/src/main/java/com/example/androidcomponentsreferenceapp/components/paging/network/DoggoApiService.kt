package com.example.androidcomponentsreferenceapp.components.paging.network

import com.example.androidcomponentsreferenceapp.components.paging.model.DoggoImageModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RemoteInjector {
    const val API_KEY = "d6fd31ff-2b46-4600-b25d-cbcd09f0ac14"
    const val API_ENDPOINT = "https://api.thedogapi.com"
    const val HEADER_API_KEY = "x-api-key"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    fun injectDoggoApiService(retrofit: Retrofit = getRetrofit()): DoggoApiService {
        return retrofit.create(DoggoApiService::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient()): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    private fun getOkHttpNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest =
                chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
            chain.proceed(newRequest)
        }
    }

    private fun getHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor = getHttpLogger(),
        okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }

    interface DoggoApiService {

        @GET("v1/images/search")
        suspend fun getDoggoImages(
            @Query("page") page: Int,
            @Query("limit") size: Int
        ): List<DoggoImageModel>
    }
}