package com.pluang.imagesearchapp.data.repository

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
import com.pluang.imagesearchapp.extension.Coroutines
import com.pluang.imagesearchapp.extension.Status
import com.pluang.imagesearchapp.paging.BaseApiService
import com.pluang.imagesearchapp.utils.LIMIT
import com.pluang.imagesearchapp.utils.NetworkHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val photoRepository: PhotoRepository,
    private val networkHelper: NetworkHelper

) : BaseApiService<Photo> {
    var networkState: MutableLiveData<Status> = MutableLiveData()

    override suspend fun loadData(offset: Int, searchKey: String): List<Photo> {

        try {
            networkState.postValue(Status.LOADING)

            Log.e("networkHelper",networkHelper.isNetworkConnected().toString())
            if (networkHelper.isNetworkConnected()) {
                val response = apiService.getImages(searchKey, LIMIT, offset)

                if (response.body()!!.photos.photo.size > 0) {
                    networkState.postValue(Status.SUCCESS)
                    return response.body()!!.photos.photo
                } else {
                    networkState.postValue(Status.SUCCESS)
                    return emptyList()
                }

            } else
            {
                val responseLocal = photoRepository.getLocalPhotos(searchKey, LIMIT, offset)

                if (responseLocal != null && responseLocal.size > 0) {
                    networkState.postValue(Status.SUCCESS)
                    return responseLocal
                } else {
                    networkState.postValue(Status.ERROR)

                    return emptyList()
                }

            }
        } catch (e: Exception) {
            networkState.postValue(Status.ERROR)
            return emptyList()
        }


    }


}

