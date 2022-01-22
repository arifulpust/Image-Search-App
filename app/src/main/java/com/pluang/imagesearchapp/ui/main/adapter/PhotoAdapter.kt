package com.pluang.imagesearchapp.ui.main.adapter
import com.pluang.imagesearchapp.R
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.databinding.PhotoItemLayoutBinding
import com.pluang.imagesearchapp.paging.BaseListItemCallback
import com.pluang.imagesearchapp.paging.BasePagingDataAdapter
import com.pluang.imagesearchapp.paging.BaseViewHolder
import com.pluang.imagesearchapp.paging.ItemComparator

class PhotoAdapter(
    cb: BaseListItemCallback<Photo>,
) : BasePagingDataAdapter<Photo>( cb , ItemComparator()) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.photo_item_layout
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let {
            holder.bind(photo, callback, position)
        }
    }
    override fun onViewRecycled(holder: BaseViewHolder) {
        if (holder.binding is PhotoItemLayoutBinding) {
            holder.binding.imgPhoto.setImageDrawable(null)
        }
        super.onViewRecycled(holder)
    }
}