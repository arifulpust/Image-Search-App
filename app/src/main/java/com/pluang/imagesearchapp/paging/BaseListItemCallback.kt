package com.pluang.imagesearchapp.paging

import android.view.View

interface BaseListItemCallback<T: Any> {
    fun onItemClicked(item: T) {}
}