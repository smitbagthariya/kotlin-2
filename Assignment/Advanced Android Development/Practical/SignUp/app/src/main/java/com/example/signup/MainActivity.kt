package com.example.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.signup.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnsignup.setOnClickListener {
            val email = binding.edtemail.text.toString().trim()
            val password = binding.edtpwd.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Plz Fill The Details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Signup successful.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e("SignUpError", "Sign up failed: ${task.exception?.message}") // Add this line
                        Toast.makeText(applicationContext, "Signup Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show() // Display the error to the user (for debugging)
                    }
                }
        }
    }
}






