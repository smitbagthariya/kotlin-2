package com.example.startquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class Signup : AppCompatActivity()
{
    lateinit var edt1 : EditText
    lateinit var edt2 : EditText
    lateinit var edt3 : EditText
    lateinit var edt4 : EditText
    lateinit var edt5 : EditText
    lateinit var btn1 : Button
    lateinit var db: SignupDatabaseClass
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        edt1 = findViewById(R.id.etd1)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)
        edt4 = findViewById(R.id.edt4)
        edt5 = findViewById(R.id.edt5)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext, SignupDatabaseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()

        btn1.setOnClickListener {

            var user = User()
            user.Fname = edt1.text.toString()
            user.Lname = edt2.text.toString()
            user.email = edt3.text.toString()
            user.password = edt4.text.toString()

            db.userDao().insertUser(user)
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}