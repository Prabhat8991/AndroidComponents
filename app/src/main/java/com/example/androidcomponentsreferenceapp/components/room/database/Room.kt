package com.example.androidcomponentsreferenceapp.components.room.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao {
    @Query("SELECT * FROM databasevideoModel")
    fun getVideoList(): LiveData<List<DatabaseVideoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseVideoModel: DatabaseVideoModel)
}

@Database(entities = [DatabaseVideoModel::class], version = 1, exportSchema = false)
abstract class VideosDatabase: RoomDatabase() {
    abstract val videoDao: VideoDao
}

private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {
    synchronized(VideosDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, VideosDatabase::class.java, "videos").build()
        }
    }
    return INSTANCE
}