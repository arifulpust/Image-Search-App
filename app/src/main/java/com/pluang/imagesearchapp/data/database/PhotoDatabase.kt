package com.pluang.imagesearchapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pluang.imagesearchapp.data.database.Dao.photoDao
import com.pluang.imagesearchapp.data.database.entities.Photo

@Database(
    entities = [
        Photo::class,
    ],
    version = 1,
    exportSchema = false)

abstract class PhotoDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): photoDao
    companion object {
        const val DB_NAME = "photo-search-app-db"
    }
}