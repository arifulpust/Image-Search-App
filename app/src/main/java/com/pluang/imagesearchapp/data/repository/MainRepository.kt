package com.pluang.imagesearchapp.data.repository


import android.util.Log
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
companion object{
    var offsetCount:Int=0
}
    override suspend fun loadData(offset: Int, searchKey: String): List<Photo> {

        try {
            networkState.postValue(Status.LOADING)
            if (networkHelper.isNetworkConnected()) {
                val response = apiService.getImages(searchKey, LIMIT, offsetCount)

                if (response.body()!!.photos.photo.size > 0) {
                    offsetCount+=response.body()!!.photos.photo.size
                    Log.d("response",response.body()!!.photos.toString())

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
                    Log.d("response",responseLocal.toString())
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

