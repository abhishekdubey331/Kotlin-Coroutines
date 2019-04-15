package com.mygatedemo.app.repositories

import com.mygatedemo.app.data.model.ApiResponse
import com.mygatedemo.app.data.remote.ApiInterface


class NetworkRepository(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getImageDataFromServer(): ApiResponse? {

        return safeApiCall(
            call = { apiInterface.getDataFromServerAsync().await() }
        )
    }


}