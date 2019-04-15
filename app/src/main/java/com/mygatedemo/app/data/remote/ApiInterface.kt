package com.mygatedemo.app.data.remote

import com.mygatedemo.app.data.model.ApiResponse
import com.mygatedemo.app.utils.Utils
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


/**
 * Created by Abhishek Dubey on 10.06.2018 at 00:14.
 * Copyright (c) 2018. All rights reserved.
 */
interface ApiInterface {


    @GET("b/5c89f7d65ce1aa6d9f9bf3a9")
    fun getDataFromServer(): Deferred<Response<ApiResponse>>

}