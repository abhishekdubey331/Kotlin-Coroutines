package com.mygatedemo.app

import android.app.Application
import com.mygatedemo.app.di.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initAll()
    }

    private fun initAll() {
        Logger.addLogAdapter(AndroidLogAdapter())
        setUpKoinModules()
    }


    private fun setUpKoinModules() {
        startKoin(
            this,
            listOf(
                AppModule().getAppModules()
            )
        )
    }

}


