package com.pluang.imagesearchapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.databinding.ActivityDetailsBinding
import com.pluang.imagesearchapp.ui.main.adapter.PhotoDetailsPagerAdapter
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import com.pluang.imagesearchapp.utils.PHOTO_PARAM
import com.pluang.imagesearchapp.utils.POSITION_PARAM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(){
    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater).apply {
            viewModel = mainViewModel
        }
        setContentView(binding.root)

        intent.let {
            mainViewModel.Photos=  it.getParcelableArrayListExtra<Photo>(PHOTO_PARAM) as ArrayList<Photo>
            mainViewModel.index=it.getIntExtra(POSITION_PARAM,0)
        }
        binding.toolbar.setNavigationOnClickListener {
        finish()

        }

      val  photoDetailsPagerAdapter = PhotoDetailsPagerAdapter(  mainViewModel.Photos!!)
        binding.photoPager.adapter = photoDetailsPagerAdapter
        binding.photoPager.setCurrentItem(mainViewModel.index,false)


    }

}