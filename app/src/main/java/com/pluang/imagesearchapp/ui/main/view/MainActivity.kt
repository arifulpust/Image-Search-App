package com.pluang.imagesearchapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.pluang.imagesearchapp.data.database.entities.Schedule
import com.pluang.imagesearchapp.data.model.User
import com.pluang.imagesearchapp.databinding.ActivityMainBinding
import com.pluang.imagesearchapp.paging.BaseListItemCallback
import com.pluang.imagesearchapp.ui.main.Listener.SchedulerClickListener
import com.pluang.imagesearchapp.ui.main.adapter.MainAdapter
import com.pluang.imagesearchapp.ui.main.adapter.ScheduleAdapter
import com.pluang.imagesearchapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.pluang.imagesearchapp.R
import android.view.inputmethod.EditorInfo

import timber.log.Timber


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseListItemCallback<User> , SchedulerClickListener {
    private var listJob: Job? = null
    private lateinit var adapters: MainAdapter
    private  var scheduleAdapter: ScheduleAdapter?=null

    private val mainViewModel by viewModels<MainViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var searchWatcher: TextWatcher? = null
    private var search: String = ""
     var offline: Boolean ?=false
     var isActive: Boolean ?=false
    private var scheduleList: List<Schedule>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapters = MainAdapter(this)
        observeListId()

        setSupportActionBar(binding.toolbar)
//        lifecycleScope.launchWhenResumed {
//            adapters.loadStateFlow.collectLatest {
//                val isLoading = it.source.refresh is LoadState.Loading || !isInitialized
//                val isEmpty = adapters.itemCount <= 0 && ! it.source.refresh.endOfPaginationReached
//                binding.placeholder.isVisible = isLoading
//                binding.recyclerView.isVisible = !isEmpty && !isLoading
//                binding.etSearch.isVisible = !isEmpty && !isLoading
//              //  binding.placeholder.showLoadingAnimation(isLoading)
//                adapters.withLoadStateFooter(ListLoadStateAdapter { adapters.retry() })
//                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                binding.recyclerView.adapter = adapters
//                isInitialized = true
//                binding.recyclerView.setHasFixedSize(true)
//            }
//        }
        binding.etSearch.setOnEditorActionListener( { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
Log.e("Done","done")
           Timber.e("done")
                true
            } else false
        })



        searchWatcher = binding.etSearch.doAfterTextChanged {
            search = it.toString()


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

    override fun onItemClicked(item: User) {
        super.onItemClicked(item)
        val shareIntent= Intent(this, DetailsActivity::class.java)
        shareIntent.putExtra("name",item.login)
        shareIntent.putExtra("id",item.id)
        shareIntent.putExtra("avatar",item.avatar_url)
        shareIntent.putExtra("note",item.note)
        startActivity(shareIntent)
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

    private fun observeListId(){
        lifecycleScope.launchWhenStarted {
            mainViewModel.getData().collectLatest {
                Log.e("UPLOAD 2", "Collecting ->>> ${it.size}")
                if(it.isNotEmpty())
                {


                    Log.e("data","data"+it.size)
                    scheduleList=it
                }
                else{

                }

            }
        }
    }
//    fun searchData( scheduleList: List<Schedule>){
//        binding.recyclerViewSearch.layoutManager =LinearLayoutManager(this)
//        scheduleAdapter = ScheduleAdapter(this, scheduleList,this)
//        binding.recyclerViewSearch.adapter = scheduleAdapter
//    }



    override fun show(user: Schedule) {
        val shareIntent= Intent(this, DetailsActivity::class.java)
        shareIntent.putExtra("name",user.login)
        shareIntent.putExtra("id",user.id)
        shareIntent.putExtra("avatar",user.avatar_url)
        shareIntent.putExtra("note",user.note)
        startActivity(shareIntent)
    }
}