package com.example.startquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class dashboard : AppCompatActivity() {
    lateinit var gridView: GridView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var toolbar: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        gridView = findViewById(R.id.gridView)
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)

        val items = listOf(
            Model(R.drawable.img, "Play Quiz"),
            Model(R.drawable.img1, "Read Questions")
        )

        val adapter = DashboardAdapter(this, items)
        gridView.adapter = adapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, ShowQuestions::class.java))
                1 -> startActivity(Intent(this, ReadQuestions::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_us -> {
                Toast.makeText(applicationContext, "About Us", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.contact_us -> {
                Toast.makeText(applicationContext, "Contect Us", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.logout -> {
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
