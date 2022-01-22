package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.models.PhotosResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getImages(searchKey:String,perPage:Int,page:Int): Response<PhotosResponse> = apiService.getImages(searchKey,perPage,page)

}