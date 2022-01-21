package com.pluang.imagesearchapp.data.repository

import android.util.Log
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.paging.BaseApiService
import com.pluang.imagesearchapp.utils.LIMIT
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val toffeeApi: ApiService,
) : BaseApiService<Photo> {

    override suspend fun loadData(offset: Int, searchKey: String): List<Photo> {
        try {
            val response = toffeeApi.getImages(LIMIT, offset, searchKey)
            Log.e("response loadData", "response" + response.body()?.photos!!.photo.size.toString())
            if (response.body()!!.photos != null && response.body()!!.photos.page != null && response.body()!!.photos.photo.size > 0)
                return response.body()!!.photos.photo
            else {
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e("response loadData", "response" + e.localizedMessage)
            return emptyList()
        }

    }


}

