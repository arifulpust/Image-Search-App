package com.pluang.imagesearchapp.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pluang.imagesearchapp.utils.LIMIT
import kotlinx.coroutines.flow.Flow

class BaseListRepositoryImpl<T: Any> constructor(
    private val pagingFactory: ()-> PagingSource<Int, T>
): BaseListRepository<T> {
    override fun getList(): Flow<PagingData<T>> {
        return Pager(
            config = PagingConfig(pageSize = LIMIT, prefetchDistance = 2),
            pagingSourceFactory = pagingFactory
        ).flow
    }

}