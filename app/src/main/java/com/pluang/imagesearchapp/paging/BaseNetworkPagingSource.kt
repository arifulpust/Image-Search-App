package com.pluang.imagesearchapp.paging
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pluang.imagesearchapp.data.repository.MainRepository.Companion.offsetCount
import com.pluang.imagesearchapp.utils.SEARCH_KEY

class BaseNetworkPagingSource<T: Any>(private val service: BaseApiService<T>): PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1

        return try {
            if((offsetCount/20+1)<(page-1))
            {
                Log.d("load->"+offsetCount,page.toString())
                LoadResult.Error(Error("error"))
            }
            else {
                val response = service.loadData(page, SEARCH_KEY)
                LoadResult.Page(
                    data = response,
                    prevKey = null,
                    nextKey = page + 1
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }
}