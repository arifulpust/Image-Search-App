package com.pluang.imagesearchapp.data.repository

import android.util.Log
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.paging.BaseApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val toffeeApi: ApiService,
) : BaseApiService<User> {

    override suspend fun loadData(offset: Int): List<User> {
        try {
            val response = toffeeApi.getUser(offset)
            Log.e("response loadData","response"+response.body()?.size)

            return response.body()!!
        } catch (e: Exception) {
            Log.e("response loadData","response"+e.localizedMessage)
            return  emptyList()
        }

    }
}

