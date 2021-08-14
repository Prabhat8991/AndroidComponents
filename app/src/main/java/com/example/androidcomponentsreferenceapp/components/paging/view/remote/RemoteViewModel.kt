package com.example.androidcomponentsreferenceapp.components.paging.view.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.androidcomponentsreferenceapp.components.paging.data.DoggoImagesRepository
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

@ExperimentalPagingApi
class RemoteViewModel (
    val repository: DoggoImagesRepository = DoggoImagesRepository()
): ViewModel() {
    /**
     * we just mapped the data received from the repository to [PagingData<String>] to show the map
     * function you can always return the original model if needed, in our case it would be [DoggoImageModel]
     */
    fun fetchDoggoImages(): Flow<PagingData<String>> {
        return repository.letDoggoImagesFlow().map {
            it.map {doggoImageModel ->
                doggoImageModel.url
            }
        }
    }
}