package com.pluang.imagesearchapp.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluang.imagesearchapp.data.database.Dao.photoDao
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.utils.Utils
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PhotoDatabaseTest : TestCase(){
    private lateinit var db: PhotoDatabase
    private lateinit var dao: photoDao


    @Before
   public override fun setUp() {
      val context=ApplicationProvider.getApplicationContext<Context>()
        db=Room.inMemoryDatabaseBuilder(context,PhotoDatabase::class.java).build()
        dao=db.getPhotoDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }
    @Test
    fun writeReadPhoto()= runBlocking {
      val spend=  Photo(1, 1, "", "", "","","cat","","","")
        dao.insert(spend)
        val photos=  dao.findPhoto(1)
      var result=  if (photos?.size>0&&photos.get(0).id==1L) true else false
        Assert.assertEquals(result, true)
    }
    @Test
    fun totalCount()= runBlocking {
        val spend=  Photo(1, 1, "", "", "","","cat","","","")
        dao.insert(spend)
        val count=  dao.getCount()
    Log.e("Count",count.toString())
        Assert.assertEquals(count>0, true)
    }
    @Test
    fun writeReadTwentyPhoto()= runBlocking {
        for(id in 1..20)
        {
            val spend=  Photo(id.toLong(), id.toLong(), "", "", "","","cat","","","")
          dao.insert(spend)

        }
       // val count=  dao.getCount()

        val count=  dao.getPhotos("cat",20,0)
        Log.e("Count",count.toString())
        Assert.assertEquals(count.size==20, true)
    }
}