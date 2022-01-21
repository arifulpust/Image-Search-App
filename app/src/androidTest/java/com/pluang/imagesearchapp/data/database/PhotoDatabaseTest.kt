package com.pluang.imagesearchapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluang.imagesearchapp.data.database.Dao.photoDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
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
        dao=db.getScheduleDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb(){

        db.close()
    }
    @Test
    fun writeReadScheduler()= runBlocking {
      val spend=  Schedule(1, "evan", "", "", "","","", "","","","","","","","","Test Note",false)
      //  dao.insert(spend)
//        val spends=  dao.getScheduleByContentId(1)
//        if (spends?.login?.contains(spend.login)!!){
//
//            Log.e("Success","Success")
//        }

    }
}