package com.coroutines.app.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.coroutines.app.R
import com.coroutines.app.data.model.ApiResponse
import com.coroutines.app.utils.ImageHelper
import com.coroutines.app.utils.ToolbarHelper
import com.coroutines.app.utils.Utils
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val IMAGE_DATA_KEY = "image_data"
    private val utils: Utils by inject()
    private lateinit var mAdapter: ImageAdapter
    private val imageHelper: ImageHelper by inject()
    private val mainActivityViewModel: MainActivityViewModel by inject()
    private val toolbarHelper: ToolbarHelper by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarHelper.setUpToolbarNoBack(this, "Rupeek")


        if (utils.verifyAvailableNetwork(this)) {
            mainActivityViewModel.loadDataFromServer()
        } else {
            if (Hawk.contains(IMAGE_DATA_KEY))
                mainActivityViewModel.loadDataFromLocalCache()
            else {
                Toast.makeText(this, "Internet required first launch", Toast.LENGTH_SHORT).show()
                progress_bar.visibility = View.GONE
            }
        }

        mainActivityViewModel.imageDataResult().observe(this, Observer<ApiResponse> {
            it?.let { res ->
                Hawk.put(IMAGE_DATA_KEY, res)
                mAdapter = ImageAdapter(res.data, imageHelper) {

                }
                image_recycler.adapter = mAdapter
                image_recycler.layoutManager = GridLayoutManager(applicationContext, 2)
                image_recycler.isNestedScrollingEnabled = false
                if (res.data.isNullOrEmpty()) {
                    Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
                }
            }
        })

        mainActivityViewModel.apiErrorEvent().observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        mainActivityViewModel.apiLoaderEvent().observe(this, Observer<Boolean> {
            utils.hideProgress(progress_bar)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityViewModel.disposeElements()
    }

}
