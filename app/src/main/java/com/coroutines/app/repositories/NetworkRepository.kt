package com.coroutines.app.repositories

import com.coroutines.app.data.model.ApiResponse
import com.coroutines.app.data.remote.ApiInterface


class NetworkRepository(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getImageDataFromServer(): ApiResponse? {

        return safeApiCall(
            call = { apiInterface.getDataFromServerAsync().await() }
        )
    }


}