package com.pluang.imagesearchapp.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.pluang.imagesearchapp.R
import kotlinx.coroutines.*


object Utils {
    var note:String?=""
    private val coroutineContext = Dispatchers.IO + SupervisorJob()


    @BindingAdapter("loadChannelImageOne")
    @JvmStatic
    fun loadChannelImageOne(view: ImageView, imageUrl: String?){

      view.load(imageUrl) {
             //   transformations(CircleCropTransformation())
                crossfade(false)
              //  fallback(R.drawable.ic_menu_profile)
               // placeholder(R.drawable.ic_menu_profile)
            }
    }
    fun isEmpty(str: String?): Boolean {
        return str == null || str!!.isEmpty() || str.trim() == ""
    }

}

