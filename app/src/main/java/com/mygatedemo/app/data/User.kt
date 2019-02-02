package com.mygatedemo.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "user_table"
)
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id_: String = "",

    @ColumnInfo(name = "userName")
    var userName: String = "",

    @ColumnInfo(name = "randomNumber")
    var randomNumber: Int = 0,

    @ColumnInfo(name = "imagePath")
    var imagePath: String=""

)
