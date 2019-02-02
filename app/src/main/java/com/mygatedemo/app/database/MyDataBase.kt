package com.mygatedemo.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mygatedemo.app.data.User


@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}