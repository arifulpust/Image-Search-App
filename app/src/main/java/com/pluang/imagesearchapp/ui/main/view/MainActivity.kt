package com.pluang.imagesearchapp.ui.main.view

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pluang.imagesearchapp.databinding.ActivityMainBinding
import com.pluang.imagesearchapp.paging.BaseListItemCallback
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.pluang.imagesearchapp.R
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.extension.hideKeyboard
import com.pluang.imagesearchapp.ui.main.adapter.PhotoAdapter
import com.pluang.imagesearchapp.utils.SEARCH_KEY


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseListItemCallback<Photo>  {
    private lateinit var adapters: PhotoAdapter

    private val mainViewModel by viewModels<MainViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var searchWatcher: TextWatcher? = null
    private var search: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapters = PhotoAdapter(this)

                binding.recPhoto.layoutManager = LinearLayoutManager(this)
        binding.recPhoto.adapter = adapters
        binding.etSearch.setOnEditorActionListener( { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
hideKeyboard()
                observeLatestDataList()
                true
            } else false
        })



        searchWatcher = binding.etSearch.doAfterTextChanged {
            search = it.toString()
            SEARCH_KEY=search.trim()

        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.collumn_two -> {
                true
            }
            R.id.collumn_three -> {
                true
            }
            R.id.collumn_four -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(item: Photo) {
        super.onItemClicked(item)
        Log.e("item",item.toString())

    }

    private fun observeLatestDataList() {
        Log.e("SEARCH_KEY",SEARCH_KEY)
     lifecycleScope.launchWhenResumed {
            adapters.refresh()
            mainViewModel.loadLatestData().collectLatest {
                adapters.submitData(it.map { channel ->
//                    try {
//                        if (scheduleList?.size!!>0 && scheduleList!=null)
//                        {
//                            for(list in scheduleList!!){
//                                if (channel.id==list.id){
//                                    Log.e("list.note","list.note"+list.note)
//                                    //    Log.e("list.note","list.note"+scheduleList?.size)
//                                    channel.apply {
//                                        note = list.note
//                                    }
//                                }
//
//
//                            }
//                        }
//                    } catch (e: Exception) {
//                    }

                   // mainViewModel.insertData(channel)
                    channel
                })
            }


        }
    }
//    private fun searchList(name:String){
//
//        binding.etSearch.visibility=View.VISIBLE
//
//        lifecycleScope.launchWhenStarted {
//            mainViewModel.getSearchData(name).collectLatest {
//                Log.e("UPLOAD 2", "Collecting ->>> ${it.size}")
//                if(it.isNotEmpty())
//                {
//                    binding.recyclerView.visibility=View.INVISIBLE
//                    binding.recyclerViewSearch.visibility=View.VISIBLE
//                    Log.e("data","data"+it.size)
//                    searchData(it)
//                }
//                else{
//                    binding.recyclerView.visibility=View.VISIBLE
//                    binding.recyclerViewSearch.visibility=View.GONE
//                }
//
//            }
//        }
//
//    }

//    private fun observeList() {
//
//        Handler().postDelayed({
//            binding.placeholder.visibility=View.GONE
//            binding.etSearch.visibility=View.VISIBLE
//            binding.recyclerViewOffline.visibility=View.VISIBLE
//            binding.recyclerView.visibility=View.GONE
//
//            lifecycleScope.launchWhenStarted {
//                mainViewModel.getData().collectLatest {
//                    Log.e("UPLOAD 2", "Collecting ->>> ${it.size}")
//                    if(it.isNotEmpty())
//                    {
//
//                        Log.e("data","data"+it.size)
//                        data(it)
//                    }
//                    else{
//
//                    }
//
//                }
//            }
//        }, 600)
//
//    }


//    fun searchData( scheduleList: List<Schedule>){
//        binding.recyclerViewSearch.layoutManager =LinearLayoutManager(this)
//        scheduleAdapter = ScheduleAdapter(this, scheduleList,this)
//        binding.recyclerViewSearch.adapter = scheduleAdapter
//    }




}