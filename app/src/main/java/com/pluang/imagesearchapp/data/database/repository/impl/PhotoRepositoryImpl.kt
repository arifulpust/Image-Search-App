package com.pluang.imagesearchapp.data.database.repository.impl

import com.pluang.imagesearchapp.data.database.Dao.photoDao
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
import com.pluang.imagesearchapp.data.database.entities.Photo
import kotlinx.coroutines.flow.Flow


class PhotoRepositoryImpl (private val dao: photoDao):
    PhotoRepository {
    override suspend fun insert(schedule: Photo): Long {
        return dao.insert(schedule)
    }

//    override suspend fun delete(schedule: Schedule): Int {
//        return dao.delete(schedule)
//    }

    override  fun getAllSchedule(): Flow<List<Photo>> {
        return dao.getAllSchedule()
    }

    override suspend fun getScheduleByContentId(contentId: Long): Photo?  {
        return dao.getScheduleByContentId(contentId)
    }

//    override  fun getScheduleByName(name: String): Flow<List<Photo>> {
//        return dao.getScheduleByName(name)
//    }


//    override suspend fun updateScheduleByContentId(
//        contentId: Long, notes: String
//    ): Int {
//        return dao.updateScheduleByContentId(contentId,notes)
//    }

    override suspend fun deleteByContentId(contentId: Long) {
        return dao.deleteByContentId(contentId)
    }
}