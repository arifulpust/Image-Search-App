package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.models.PhotosResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getImages(perPage:Int,page:Int,searchKey:String): Response<PhotosResponse>
}