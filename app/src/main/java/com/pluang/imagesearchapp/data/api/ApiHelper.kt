package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.models.PhotosResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getImages(searchKey:String,perPage:Int,page:Int): Response<PhotosResponse>
}