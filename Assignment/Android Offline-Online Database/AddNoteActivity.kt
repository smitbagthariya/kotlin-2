package com.example.note

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class AddNoteActivity : AppCompatActivity()
{
    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var btn1: Button
    lateinit var db: MyDbClass

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext, MyDbClass::class.java, "notesDatabase")
            .allowMainThreadQueries()
            .build()

        btn1.setOnClickListener {

            var title = edt1.text.toString()
            var description = edt2.text.toString()

            var note = Note()
            note.title = title
            note.description = description

            db.daoClass().insertdata(note)
            Toast.makeText(applicationContext, "Note Added", Toast.LENGTH_SHORT).show()

            var i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }
}