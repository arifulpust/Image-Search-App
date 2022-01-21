package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.models.PhotosResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getImages(perPage:Int,page:Int,searchKey:String): Response<PhotosResponse> = apiService.getImages(perPage,page,searchKey)

}