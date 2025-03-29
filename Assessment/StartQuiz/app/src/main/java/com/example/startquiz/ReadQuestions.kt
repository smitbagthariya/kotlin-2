package com.example.startquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReadQuestions : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_questions)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val categories = listOf(
            CategoryModel("Fundamentals", R.drawable.img_2),
            CategoryModel("SQL", R.drawable.img_3),
            CategoryModel("HR Questions", R.drawable.hr)
        )

        val adapter = CategoryAdapter(this, categories)
        recyclerView.adapter = adapter

    }


}
