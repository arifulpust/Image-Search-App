package com.pluang.imagesearchapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pluang.imagesearchapp.databinding.ActivityDetailsBinding
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(){
    private var listJob: Job? = null
    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun observeLatestDataList(name: String?)
    {
        listJob?.cancel()
        listJob = lifecycleScope.launchWhenResumed {



    }
    }
}