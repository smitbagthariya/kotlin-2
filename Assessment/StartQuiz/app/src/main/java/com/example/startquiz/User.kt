package com.example.startquiz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "FirstName")
    var Fname: String = ""

    @ColumnInfo(name = "LastName")
    var Lname: String = ""

    @ColumnInfo(name = "Email")
    var email: String = ""

    @ColumnInfo(name = "Password")
    var password: String = ""
}
