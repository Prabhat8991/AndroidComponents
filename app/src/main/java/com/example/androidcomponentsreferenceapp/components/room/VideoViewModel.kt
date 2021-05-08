package com.example.androidcomponentsreferenceapp.components.room

import android.app.Application
import androidx.lifecycle.*
import com.example.androidcomponentsreferenceapp.components.room.database.getDatabase
import com.example.androidcomponentsreferenceapp.components.room.repository.VideoRepository
import kotlinx.coroutines.launch

class VideoViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val videosRepository = VideoRepository(database)

    private var _isError : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val error: LiveData<Boolean>
      get() = _isError

    init {
        viewModelScope.launch {
            try {
                videosRepository.refreshVideos()
            } catch (exception: Exception) {
                _isError.value = true
            }
        }
    }

    val playList = videosRepository.videos


    class Factory(val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(VideoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}