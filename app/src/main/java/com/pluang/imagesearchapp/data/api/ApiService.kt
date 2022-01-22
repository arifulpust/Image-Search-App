package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.models.PhotosResponse
import com.pluang.imagesearchapp.utils.KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key="+KEY+"&format=json&nojsoncallback=1&sort=relevance")
    suspend fun getImages(
        @Query("text") searchKey: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<PhotosResponse>


}