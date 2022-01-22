package com.pluang.imagesearchapp.ui.main.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.pluang.imagesearchapp.R
import com.pluang.imagesearchapp.data.database.entities.Photo
import kotlinx.android.synthetic.main.photo_details_layout.view.*


class PhotoDetailsPagerAdapter (val photos: List<Photo>) : RecyclerView.Adapter<PhotoDetailsPagerAdapter
.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cellForRow = inflater.inflate(R.layout.photo_details_layout, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder:CustomViewHolder, position: Int) {

        holder.itemView.img_search.load(photos[position].imageUrl()) {
            crossfade(false)
        }

    }
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }
}