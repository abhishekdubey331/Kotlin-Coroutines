package com.mygatedemo.app

import android.app.Application
import com.mygatedemo.app.di.AppModule
import com.orhanobut.hawk.Hawk
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
        Hawk.init(this).build();
        setUpKoinModules()
    }


    private fun setUpKoinModules() {
        startKoin(
            this,
            listOf(
                AppModule().getAppModules(),
                /*AppModule().getNetModule("http://www.mocky.io/"),*/
                AppModule().getNetModule("https://api.jsonbin.io/")
            )
        )
    }

}


