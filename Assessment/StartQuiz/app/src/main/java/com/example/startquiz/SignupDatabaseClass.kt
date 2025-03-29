package com.example.startquiz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Quiz::class], version = 1, exportSchema = false)
abstract class SignupDatabaseClass : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: SignupDatabaseClass? = null

        fun getDatabase(context: Context): SignupDatabaseClass {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SignupDatabaseClass::class.java,
                    "myDatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
