package com.example.note

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class MyDbClass: RoomDatabase()
{
    abstract fun daoClass(): MyDao
}