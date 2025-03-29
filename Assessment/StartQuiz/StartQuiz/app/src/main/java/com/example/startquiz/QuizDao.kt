package com.example.startquiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz ORDER BY id ASC")
    fun getAllQuestions(): List<Quiz>

    @Insert
    fun insertQuestions(questions: List<Quiz>)

    @Query("DELETE FROM quiz")
    fun clearQuestions()

}
