package com.mygatedemo.app.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mygatedemo.app.activities.MainActivityViewModel
import com.mygatedemo.app.data.remote.ApiInterface
import com.mygatedemo.app.repositories.NetworkRepository
import com.mygatedemo.app.utils.ImageHelper
import com.mygatedemo.app.utils.ToolbarHelper
import com.mygatedemo.app.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppModule {


    fun getAppModules(): Module {

        return module {
            single { NetworkRepository(get()) }
            single { Utils(get()) }
            single { ToolbarHelper() }
            single { ImageHelper(get()) }
            viewModel { MainActivityViewModel(get()) }
        }
    }


    fun getNetModule(baseUrl: String) = module {
        single {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }
        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        single { get<Retrofit>().create(ApiInterface::class.java) }
    }

}
