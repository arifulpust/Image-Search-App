package com.pluang.imagesearchapp.paging

interface BaseApiService<T: Any> {
    suspend fun loadData(offset: Int): List<T>
}