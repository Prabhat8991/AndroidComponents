package com.example.androidcomponentsreferenceapp.components.room.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidcomponentsreferenceapp.components.room.model.VideoDomainModel

@Entity
data class DatabaseVideoModel(
    @PrimaryKey
    val title: String,
    val thumbnail: String
)
fun List<DatabaseVideoModel>.asDomainModel(): List<VideoDomainModel> {
    return map {
        VideoDomainModel(
            title = it.title,
            thumbnail = it.thumbnail
        )
    }
}