package com.pluang.imagesearchapp.data.database.repository

import com.pluang.imagesearchapp.data.database.entities.Schedule
import kotlinx.coroutines.flow.Flow


interface ScheduleRepository {

    suspend fun insert(schedule: Schedule): Long

    //suspend fun delete(schedule: Schedule): Int<List<Schedule>>

     suspend fun getScheduleByContentId(contentId: Long): Schedule?

    fun getAllSchedule(): Flow<List<Schedule>>
     fun getScheduleByName(name: String): Flow<List<Schedule>>

    suspend fun updateScheduleByContentId(contentId: Long, notes: String): Int

    suspend fun deleteByContentId(contentId: Long)
}