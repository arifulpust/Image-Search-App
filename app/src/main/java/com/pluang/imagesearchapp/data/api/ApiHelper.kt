package com.pluang.imagesearchapp.data.api

import com.pluang.imagesearchapp.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}