package com.pluang.imagesearchapp.paging

interface BaseApiService<T: Any> {
    suspend fun loadData(offset: Int,searchKey:String): List<T>
}