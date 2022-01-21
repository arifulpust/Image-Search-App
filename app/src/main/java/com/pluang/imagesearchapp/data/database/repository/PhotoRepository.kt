package com.pluang.imagesearchapp.data.database.repository

import com.pluang.imagesearchapp.data.database.entities.Photo
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    suspend fun insert(schedule: Photo): Long

    //suspend fun delete(schedule: Schedule): Int<List<Schedule>>

     suspend fun getScheduleByContentId(contentId: Long): Photo?

    fun getAllSchedule(): Flow<List<Photo>>
   //  fun getScheduleByName(name: String): Flow<List<Photo>>

   // suspend fun updateScheduleByContentId(contentId: Long, notes: String): Int

    suspend fun deleteByContentId(contentId: Long)
}