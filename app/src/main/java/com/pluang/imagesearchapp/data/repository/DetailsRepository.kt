package com.pluang.imagesearchapp.data.repository

import android.util.Log
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.paging.BaseApiService
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val toffeeApi: ApiService,
)  {

     suspend fun loadData(name: String?): User? {
        try {
            val response = toffeeApi.getUserDetails(name)
            Log.e("response loadData","response"+response.isSuccessful)

            return response.body()!!
        } catch (e: Exception) {
            Log.e("response loadData","response"+e.localizedMessage)
            return  null
        }

    }
}