package com.pluang.imagesearchapp.di.module

import android.content.Context
import androidx.room.Room
import com.pluang.imagesearchapp.data.database.Dao.ScheduleDao
import com.pluang.imagesearchapp.data.database.SchedulerDatabase
import com.pluang.imagesearchapp.data.database.repository.ScheduleRepository
import com.pluang.imagesearchapp.data.database.repository.impl.ScheduleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext app: Context): SchedulerDatabase {
        return Room.databaseBuilder(app,
            SchedulerDatabase::class.java, SchedulerDatabase.DB_NAME)
            .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesSchedulerDao(db: SchedulerDatabase): ScheduleDao {
        return db.getScheduleDao()
    }


    @Singleton
    @Provides
    fun providesSchedulerRepository(dao: ScheduleDao): ScheduleRepository {
        return ScheduleRepositoryImpl(dao)
    }
}