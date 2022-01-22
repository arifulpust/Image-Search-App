package com.pluang.imagesearchapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pluang.imagesearchapp.data.database.Dao.photoDao
import com.pluang.imagesearchapp.data.database.PhotoDatabase
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.data.repository.MainRepository
import com.pluang.imagesearchapp.extension.Status
import com.pluang.imagesearchapp.paging.BaseListRepositoryImpl
import com.pluang.imagesearchapp.paging.BaseNetworkPagingSource
import com.pluang.imagesearchapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     val mainRepository: MainRepository,
    private val photoRepository: PhotoRepository

) : ViewModel() {
     var Photos: MutableList<Photo> = arrayListOf()
    var index:Int=0
    fun loadLatestData(): Flow<PagingData<Photo>> {
        return BaseListRepositoryImpl({
            BaseNetworkPagingSource(
                mainRepository
            )
        }).getList().cachedIn(viewModelScope)
    }
    fun insertPhoto(data: Photo) {
        viewModelScope.launch {
            try {
                val photos= photoRepository. getItemId(data.id)
                if(photos==null||photos.size==0)
                {
               photoRepository.insert(data)
                }
            }
            catch (e:Exception)
            {
            }


        }
    }

}