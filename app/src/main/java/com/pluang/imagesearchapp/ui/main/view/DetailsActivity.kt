package com.pluang.imagesearchapp.ui.main.view
import androidx.fragment.app.activityViewModels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.databinding.ActivityDetailsBinding
import com.pluang.imagesearchapp.ui.main.adapter.PhotoDetailsPagerAdapter
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import com.pluang.imagesearchapp.utils.PHOTO_KEY
import com.pluang.imagesearchapp.utils.POSITION_KEY
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
            mainViewModel.Photos=  it.getParcelableArrayListExtra<Photo>(PHOTO_KEY) as ArrayList<Photo>
            mainViewModel.index=it.getIntExtra(POSITION_KEY,0)
            Log.e("photo size", mainViewModel.Photos.size.toString()+"  "+ mainViewModel.index)

        }
        binding.toolbar.setNavigationOnClickListener {
        finish()

        }

      var  photoDetailsPagerAdapter = PhotoDetailsPagerAdapter(  mainViewModel.Photos!!)
        binding.photoPager.adapter = photoDetailsPagerAdapter
        binding.photoPager.setCurrentItem(mainViewModel.index,false)

        binding.photoPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


    }

}