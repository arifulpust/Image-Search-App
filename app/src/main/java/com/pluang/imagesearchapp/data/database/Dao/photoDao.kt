package com.pluang.imagesearchapp.data.database.Dao

import androidx.room.*
import com.pluang.imagesearchapp.data.database.entities.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface photoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo): Long

    @Delete
    suspend fun delete(photo: Photo): Int

    @Query("SELECT * FROM photo")
    fun getAllSchedule(): Flow<List<Photo>>

   // @Query("Select * from photo where  login LIKE :name OR  note LIKE :name")
  //  @Query("Select * from photo ")
  //  fun getScheduleByName(name: String): Flow<List<Photo>>

    @Query("SELECT * FROM photo WHERE id == :contentId")
    fun getScheduleByContentId(contentId: Long): Photo?

    @Query("DELETE FROM photo WHERE id ==:contentId")
    suspend fun deleteByContentId(contentId: Long)
}