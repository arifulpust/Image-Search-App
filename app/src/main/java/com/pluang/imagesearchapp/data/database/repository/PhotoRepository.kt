package com.pluang.imagesearchapp.data.database.repository

import com.pluang.imagesearchapp.data.database.entities.Photo
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    suspend fun insert(schedule: Photo): Long
     suspend  fun getItemId(item:Long):List<Photo>
    suspend fun deleteAllPhotos()
    suspend fun getTotalPhotos():Long
    suspend fun getLocalPhotos(searckKey:String,limit: Int,offset:Int): List<Photo>
}