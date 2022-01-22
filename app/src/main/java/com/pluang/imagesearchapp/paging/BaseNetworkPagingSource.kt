package com.pluang.imagesearchapp.paging
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pluang.imagesearchapp.utils.SEARCH_KEY

class BaseNetworkPagingSource<T: Any>(private val service: BaseApiService<T>): PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        return try {
            val response = service.loadData(page,SEARCH_KEY)
            LoadResult.Page(
                data = response,
                prevKey =null,
                nextKey =  page + 1
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }
}