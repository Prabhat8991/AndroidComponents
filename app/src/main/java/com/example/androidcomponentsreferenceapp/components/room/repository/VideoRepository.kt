package com.example.androidcomponentsreferenceapp.components.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.androidcomponentsreferenceapp.components.room.database.VideosDatabase
import com.example.androidcomponentsreferenceapp.components.room.database.asDomainModel
import com.example.androidcomponentsreferenceapp.components.room.model.VideoDomainModel
import com.example.androidcomponentsreferenceapp.components.room.network.Network
import com.example.androidcomponentsreferenceapp.components.room.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(private val database: VideosDatabase) {
    //Transformation maps are used to transform LiveData

    val videos: LiveData<List<VideoDomainModel>> = Transformations.map(database.videoDao.getVideoList()) {
        it.asDomainModel()
    }
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.videoService.getPlayList().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}