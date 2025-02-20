package com.example.note

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class UpdateNoteActivity : AppCompatActivity()
{
    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var btn1: Button
    lateinit var db: MyDbClass
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext, MyDbClass::class.java, "notesDatabase")
            .allowMainThreadQueries()
            .build()

        id = intent.getIntExtra("id", 0)
        edt1.setText(intent.getStringExtra("title"))
        edt2.setText(intent.getStringExtra("description"))

        btn1.setOnClickListener {

            var note = Note()
            note.id = id
            note.title = edt1.text.toString()
            note.description = edt2.text.toString()

            db.daoClass().updatedata(note)
            Toast.makeText(applicationContext, "Note Updated", Toast.LENGTH_SHORT).show()

            var i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }
}