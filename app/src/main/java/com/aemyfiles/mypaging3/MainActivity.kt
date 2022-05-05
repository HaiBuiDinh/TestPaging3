package com.example.mypaging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemyfiles.mypaging3.R
import com.example.mypaging3.data.APIService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var mainListAdapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupList()
        setupView()

//        testGet()
    }

    private fun setupView() {
        lifecycleScope.launchWhenCreated {
            viewModel.listData.collectLatest {
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupViewModel() {
        val connect = APIService.getApiService()
        viewModel = ViewModelProvider(this, MainViewModelFactory(connect))[MainViewModel::class.java]
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    private fun testGet() {
        val connect = APIService.getApiService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = connect.getListData(1)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("hai.bui1", "testGet: success")
                } else {
                    Log.d("hai.bui1", "testGet: fail")
                }
            }
        }
    }
}