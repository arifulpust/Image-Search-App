package com.pluang.imagesearchapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pluang.imagesearchapp.data.database.Dao.ScheduleDao
import com.pluang.imagesearchapp.data.database.entities.Schedule

@Database(
    entities = [
        Schedule::class,

    ],
    version = 3,
    exportSchema = false)

abstract class SchedulerDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDao


    companion object {
        const val DB_NAME = "schedule-db"
    }
}