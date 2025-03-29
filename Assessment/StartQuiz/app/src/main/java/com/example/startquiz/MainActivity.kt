package com.example.startquiz

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnSignup: Button
    lateinit var db: SignupDatabaseClass
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.edt1)
        edtPassword = findViewById(R.id.edt2)
        btnLogin = findViewById(R.id.btn1)
        btnSignup = findViewById(R.id.btn2)

        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)

        val savedEmail = sharedPreferences.getString("email", null)
        if (savedEmail != null) {
            startActivity(Intent(this, dashboard::class.java))
            finish()
        }

        db = Room.databaseBuilder(applicationContext, SignupDatabaseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration() // Add this line
            .build()


        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            val userDao = db.userDao()
            val user = userDao.getUserByEmail(email)

            if (user != null && user.password == password) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.apply()

                startActivity(Intent(this, dashboard::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignup.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            finish()
        }
    }
}
