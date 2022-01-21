package com.pluang.imagesearchapp.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pluang.imagesearchapp.data.database.entities.Schedule
import com.pluang.imagesearchapp.data.database.repository.ScheduleRepository
import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.data.repository.DetailsRepository
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
    private val detailsRepository: DetailsRepository,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users


    fun loadLatestData(): Flow<PagingData<User>> {
        return BaseListRepositoryImpl({
            BaseNetworkPagingSource(
                mainRepository
            )
        }).getList().cachedIn(viewModelScope)
    }


    suspend fun getContentData(id: Long): Schedule? {

        return   try {
             scheduleRepository.getScheduleByContentId(id)!!
        } catch (e: Exception) {
            return null
        }
    }
    suspend  fun getDetails(name: String?) : User{
        return detailsRepository.loadData(name)!!
    }
    fun getData(): Flow<List<Schedule>> {
        return scheduleRepository.getAllSchedule()
    }
    fun getSearchData(name:String): Flow<List<Schedule>> {
        return scheduleRepository.getScheduleByName(name)
    }
    fun insertData(data: User) {
        viewModelScope.launch {
            val schdule=  getContentData(data.id)
            if (schdule==null){
                scheduleRepository.insert(Schedule(data.id, data.login, data.node_id, data.avatar_url, data.gravatar_id,data.url,data.html_url, data.followers_url,data.starred_url,data.subscriptions_url,data.organizations_url,data.repos_url,data.events_url,data.received_events_url,data.type,"",data.site_admin))
            }

        }
    }
    fun updateData(note: String,id:Long) {
        viewModelScope.launch {
            scheduleRepository.updateScheduleByContentId(id,note)


        }
    }
}