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

    override suspend  fun getItemId(item:Long):List<Photo>
    {
      return  dao.findPhoto(item)!!
    }

    override suspend fun deleteAllPhotos() {
        return  dao.deleteAll()
    }

    override suspend fun getTotalPhotos():Long {
        return  dao.getCount()
    }

    override suspend fun getLocalPhotos(searckKey: String, limit: Int, offset: Int): List<Photo> {
        return  dao.getPhotos(searckKey,limit,offset)
    }


}