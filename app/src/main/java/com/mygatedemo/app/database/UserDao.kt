package com.mygatedemo.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mygatedemo.app.data.User


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM user_table")
    fun loadUsersFromDb(): List<User>




}