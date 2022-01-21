package com.pluang.imagesearchapp.ui.main.adapter

import android.view.View
import com.pluang.imagesearchapp.R

import kotlinx.android.synthetic.main.item_layout.view.*

import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.databinding.ItemLayoutBinding
import com.pluang.imagesearchapp.paging.BaseListItemCallback
import com.pluang.imagesearchapp.paging.BasePagingDataAdapter
import com.pluang.imagesearchapp.paging.BaseViewHolder
import com.pluang.imagesearchapp.paging.ItemComparator

class MainAdapter(
      cb: BaseListItemCallback<User>,
) : BasePagingDataAdapter<User>( cb , ItemComparator()) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_layout
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val obj = getItem(position)
        obj?.let {
            if (holder.binding is ItemLayoutBinding) {
                holder.binding.container.setOnClickListener {
                    callback?.onItemClicked(obj)
                }
                val pos=position +1
                if(pos % 4  == 0){
                    holder.binding.imageViewAvataone.visibility=View.VISIBLE
                    holder.binding.imageViewAvatar.visibility=View.INVISIBLE
                }
                else{
                    holder.binding.imageViewAvatar.visibility=View.VISIBLE
                    holder.binding.imageViewAvataone.visibility=View.INVISIBLE
                }
            }
            holder.bind(obj, callback, position, 15)

        }
    }
    override fun onViewRecycled(holder: BaseViewHolder) {
        if (holder.binding is ItemLayoutBinding) {
            holder.binding.imageViewAvatar.setImageDrawable(null)
        }
        super.onViewRecycled(holder)
    }
}