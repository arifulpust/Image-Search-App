package com.pluang.imagesearchapp.ui.main.view

import android.os.Bundle
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pluang.imagesearchapp.databinding.ActivityMainBinding
import com.pluang.imagesearchapp.paging.BaseListItemCallback
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.pluang.imagesearchapp.R
import com.pluang.imagesearchapp.data.database.entities.Photo
import com.pluang.imagesearchapp.extension.Status
import com.pluang.imagesearchapp.extension.hideKeyboard
import com.pluang.imagesearchapp.ui.main.adapter.PhotoAdapter
import android.content.Intent
import com.pluang.imagesearchapp.data.repository.MainRepository.Companion.offsetCount
import com.pluang.imagesearchapp.utils.*
import com.pluang.imagesearchapp.utils.Utils.isEmpty
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseListItemCallback<Photo> {
    private lateinit var photoAdapter: PhotoAdapter

    @Inject
    lateinit var networkHelper: NetworkHelper
    private val mainViewModel by viewModels<MainViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var searchWatcher: TextWatcher? = null
    private var search: String = ""
    var collumn: Int = 2
    var gridLayoutManager: GridLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = mainViewModel
        }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        photoAdapter = PhotoAdapter(this)
        gridLayoutManager = GridLayoutManager(applicationContext, collumn)
        binding.recPhoto.apply {
            layoutManager = gridLayoutManager
            adapter = photoAdapter
        }
        binding.etSearch.setOnEditorActionListener({ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                observeLatestDataList()
                true
            } else false
        })
        searchWatcher = binding.etSearch.doAfterTextChanged {
            search = it.toString()
            SEARCH_KEY = search.trim()
        }
        NetworkConnection(this).observe(this, Observer {
            if(it&&mainViewModel.Photos.size>0)
            {
                observeLatestDataList()
            }
        })
    }

    fun loadingState() {
        mainViewModel.mainRepository.networkState.observe(this, Observer { state ->
            when (state) {
                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.progressbar.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.collumn_two -> {
                changeSpan(2)
                true
            }
            R.id.collumn_three -> {
                changeSpan(3)
                true
            }
            R.id.collumn_four -> {
                changeSpan(4)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun changeSpan(count: Int) {
        if (collumn != count) {
            collumn = count
            gridLayoutManager?.setSpanCount(collumn)
            photoAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClicked(item: Photo) {
        super.onItemClicked(item)
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putParcelableArrayListExtra(PHOTO_PARAM, ArrayList(mainViewModel.Photos))
        intent.putExtra(POSITION_PARAM, mainViewModel.Photos.indexOf(item))
        startActivity(intent)
    }

    private fun observeLatestDataList() {
        if (isEmpty(SEARCH_KEY)) {
            binding.recPhoto.visibility = View.GONE
            return
        }
        offsetCount=0

        lifecycleScope.launchWhenResumed {
            photoAdapter.refresh()
            binding.recPhoto.visibility = View.VISIBLE
            loadingState()
            if (mainViewModel.Photos.size > 0)
                mainViewModel.Photos.clear()
            mainViewModel.loadLatestData().collectLatest {
                photoAdapter.submitData(

                    it.map { photo ->
                    mainViewModel.Photos.add(photo)
                    if (networkHelper.isNetworkConnected()) {
                        mainViewModel.insertPhoto(photo)
                    }

                    photo
                })
            }


        }
    }


}