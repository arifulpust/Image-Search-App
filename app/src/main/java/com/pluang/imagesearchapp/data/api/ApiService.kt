package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    @GET("users?")
    suspend fun getUser(
        @Query("since") offset: Int,
    ): Response<List<User>>

    @GET("users/{name}")
    suspend fun getUserDetails(
        @Path("name") name: String?,
    ): Response<User>
}