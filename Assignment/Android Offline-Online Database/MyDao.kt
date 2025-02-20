package com.example.note


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao
{
    @Insert
    fun insertdata(note: Note)

    @Query("select * from notes")
    fun viewdata(): MutableList<Note>

    @Query("delete from notes where id=:id")
    fun deletedata(id:Int)

    @Update
    fun updatedata(note: Note)
}