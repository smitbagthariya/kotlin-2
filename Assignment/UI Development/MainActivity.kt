package com.example.uiassignment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var ed_name :EditText
    lateinit var et_email : EditText
    lateinit var et_phone : EditText
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ed_name = findViewById(R.id.et_name)
        et_email = findViewById(R.id.et_email)
        et_phone = findViewById(R.id.et_phone)
        button = findViewById(R.id.btn_submit)

        button.setOnClickListener {
            val name = ed_name.text.toString().trim()
            val email = et_email.text.toString().trim()
            val phone = et_phone.text.toString().trim()

            when {
                name.isEmpty() -> {
                    ed_name.error = "Name is required"
                }
                email.isEmpty() -> {
                    et_email.error = "Email is required"
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    et_email.error = "Enter a valid email"
                }
                phone.isEmpty() -> {
                    et_phone.error = "Phone number is required"
                }
                !phone.matches(Regex("^[+]?[0-9]{10,13}\$")) -> {
                    et_phone.error = "Enter a valid phone number"
                }
                else -> {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}