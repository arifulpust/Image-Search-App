package com.pluang.imagesearchapp.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pluang.imagesearchapp.data.database.repository.PhotoRepository
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.data.repository.MainRepository
import com.pluang.imagesearchapp.paging.BaseListRepositoryImpl
import com.pluang.imagesearchapp.paging.BaseNetworkPagingSource
import com.pluang.imagesearchapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<Photo>>>()
    val users: LiveData<Resource<List<Photo>>>
        get() = _users


    fun loadLatestData(): Flow<PagingData<Photo>> {
        return BaseListRepositoryImpl({
            BaseNetworkPagingSource(
                mainRepository
            )
        }).getList().cachedIn(viewModelScope)
    }




//    fun getData(): Flow<List<Schedule>> {
//        return scheduleRepository.getAllSchedule()
//    }
//    fun getSearchData(name:String): Flow<List<Schedule>> {
//        return scheduleRepository.getScheduleByName(name)
//    }
    fun insertData(data: Photo) {
        viewModelScope.launch {
//            val schdule=  getContentData(data.id)
//            if (schdule==null){
//                scheduleRepository.insert(Schedule(data.id, data.login, data.node_id, data.avatar_url, data.gravatar_id,data.url,data.html_url, data.followers_url,data.starred_url,data.subscriptions_url,data.organizations_url,data.repos_url,data.events_url,data.received_events_url,data.type,"",data.site_admin))
//            }

        }
    }

}