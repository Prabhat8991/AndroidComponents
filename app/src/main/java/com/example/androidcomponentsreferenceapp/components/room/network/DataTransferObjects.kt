package com.example.androidcomponentsreferenceapp.components.room.network

import com.example.androidcomponentsreferenceapp.components.room.database.DatabaseVideoModel
import com.example.androidcomponentsreferenceapp.components.room.model.VideoDomainModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)

/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<VideoDomainModel> {
    return videos.map {
        VideoDomainModel(
            title = it.title,
            thumbnail = it.thumbnail)
    }
}

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideoModel> {
    return videos.map {
        DatabaseVideoModel(
            title = it.title,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}
