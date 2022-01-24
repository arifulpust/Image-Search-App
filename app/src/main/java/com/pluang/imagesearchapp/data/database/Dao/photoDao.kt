package com.pluang.imagesearchapp.data.database.Dao
import androidx.room.*
import com.pluang.imagesearchapp.data.database.entities.Photo

@Dao
interface photoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo): Long
    @Query("SELECT * FROM photo WHERE id = :id LIMIT 1")
    suspend fun findPhoto(id: Long): List<Photo>
    @Query("DELETE FROM photo")
    fun deleteAll()

    @Query("SELECT COUNT(*) from photo")
    fun getCount(): Long
   @Query("SELECT * FROM photo where  title LIKE '%' || :searchKey || '%'   order by title  ASC ,save_id limit :limit OFFSET :offset")
   fun getPhotos(searchKey:String?,limit:Int,offset:Int): List<Photo>


}