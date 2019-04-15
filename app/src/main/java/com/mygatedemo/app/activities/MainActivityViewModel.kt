package com.mygatedemo.app.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mygatedemo.app.data.model.ApiResponse
import com.mygatedemo.app.repositories.NetworkRepository
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivityViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private var apiResult: MutableLiveData<ApiResponse> = MutableLiveData()
    private var apiError: MutableLiveData<String> = MutableLiveData()
    private var apiLoader: MutableLiveData<Boolean> = MutableLiveData()
    private val IMAGE_DATA_KEY = "image_data"

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun imageDataResult(): LiveData<ApiResponse> {
        return apiResult
    }

    fun apiErrorEvent(): LiveData<String> {
        return apiError
    }

    fun apiLoaderEvent(): LiveData<Boolean> {
        return apiLoader
    }

    fun loadDataFromServer() {
        scope.launch {
            val t = networkRepository.getImageDataFromServer()
            apiResult.postValue(t)
            apiLoader.postValue(false)
        }
    }


    fun loadDataFromLocalCache() {
        val data = Hawk.get<ApiResponse>(IMAGE_DATA_KEY)
        data?.let {
            apiResult.postValue(data)
            apiLoader.postValue(false)
        }
    }


    fun disposeElements() {
       coroutineContext.cancel()
    }

}
