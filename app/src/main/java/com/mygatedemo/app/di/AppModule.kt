package com.mygatedemo.app.di

import androidx.room.Room
import com.mygatedemo.app.activities.MainActivityViewModel
import com.mygatedemo.app.database.MyDatabase
import com.mygatedemo.app.repositories.UserRepository
import com.mygatedemo.app.utils.AlertDialogHelper
import com.mygatedemo.app.utils.Utils
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


class AppModule {


    fun getAppModules(): Module {

        return module {
            single { UserRepository(get()) }
            single { Utils(get()) }
            single { AlertDialogHelper(get()) }
            single {
                Room.databaseBuilder(get(), MyDatabase::class.java, "myGateDb").allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            single { get<MyDatabase>().getUserDao() }
            viewModel { MainActivityViewModel(get()) }
        }
    }

}
