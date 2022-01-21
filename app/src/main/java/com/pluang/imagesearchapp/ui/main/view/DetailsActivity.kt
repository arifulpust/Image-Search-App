package com.pluang.imagesearchapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.databinding.ActivitySchedulerBinding
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import com.pluang.imagesearchapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import java.lang.Exception


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(){
    private var listJob: Job? = null
    private var _binding: ActivitySchedulerBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    private var noteWatcher: TextWatcher? = null
    private var note: String = ""
    private var data: User?=null
    private var name: String = ""
    private var avatar: String = ""
    private var id: Long =0
    private var offline: Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySchedulerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun observeLatestDataList(name: String?)
    {
        listJob?.cancel()
        listJob = lifecycleScope.launchWhenResumed {



    }
    }
}