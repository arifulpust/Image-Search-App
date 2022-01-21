package com.pluang.imagesearchapp.di.module

import android.content.Context
import androidx.room.Room
import com.pluang.imagesearchapp.data.database.Dao.photoDao
import com.pluang.imagesearchapp.data.database.PhotoDatabase
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
import com.pluang.imagesearchapp.data.database.repository.impl.PhotoRepositoryImpl
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
    fun providesDatabase(@ApplicationContext app: Context): PhotoDatabase {
        return Room.databaseBuilder(app,
            PhotoDatabase::class.java, PhotoDatabase.DB_NAME)
            .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesSchedulerDao(db: PhotoDatabase): photoDao {
        return db.getScheduleDao()
    }


    @Singleton
    @Provides
    fun providesSchedulerRepository(dao: photoDao): PhotoRepository {
        return PhotoRepositoryImpl(dao)
    }
}