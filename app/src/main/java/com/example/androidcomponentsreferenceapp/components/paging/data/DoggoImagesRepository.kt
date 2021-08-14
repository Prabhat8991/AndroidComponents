package com.example.androidcomponentsreferenceapp.components.paging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidcomponentsreferenceapp.components.paging.model.DoggoImageModel
import com.example.androidcomponentsreferenceapp.components.paging.network.RemoteInjector
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class DoggoImagesRepository(val doggoApiService: RemoteInjector.DoggoApiService = RemoteInjector.injectDoggoApiService()) {
 companion object {
     const val DEFAULT_PAGE_SIZE = 20
     const val DEFAULT_PAGE_INDEX = 1
 }
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        )
    }

    fun letDoggoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<DoggoImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                DoggoImagePagingSource(doggoApiService)
            }
        ).flow
    }
}