package com.mygatedemo.app.repositories

import com.mygatedemo.app.data.model.ApiResponse
import com.mygatedemo.app.data.remote.ApiInterface


class NetworkRepository(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getImageDataFromServer(): ApiResponse? {

        val movieResponse = safeApiCall(
            call = { apiInterface.getDataFromServer().await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        return movieResponse
    }


}