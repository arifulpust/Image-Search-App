package com.pluang.imagesearchapp.data.repository


import androidx.lifecycle.MutableLiveData
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
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

                if (responseLocal.size > 0) {
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

