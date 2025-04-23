@file:Suppress("DEPRECATION")

package com.example.fab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}