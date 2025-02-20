package com.example.note

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MyAdapter(var context: Context, var list:MutableList<Note>) : RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
    {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.design, parent, false)
        return MyView(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int)
    {
        holder.txt1.text = list[position].title
        holder.txt2.text = list[position].description

        holder.itemView.setOnClickListener {

            var alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Select Operation")
            alert.setPositiveButton("Update") { dialogInterface: DialogInterface, i: Int ->
                var intent = Intent(context, UpdateNoteActivity::class.java)
                intent.putExtra("id", list[position].id)
                intent.putExtra("title", list[position].title)
                intent.putExtra("description", list[position].description)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            alert.setNegativeButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                var db = Room.databaseBuilder(context, MyDbClass::class.java, "notesDatabase")
                    .allowMainThreadQueries()
                    .build()

                db.daoClass().deletedata(list[position].id)
                list.removeAt(position)
                notifyDataSetChanged()
            }
            alert.show()
        }
    }
}

class MyView(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var txt1: TextView
    var txt2: TextView

    init
    {
        txt1 = itemView.findViewById(R.id.txt1)
        txt2 = itemView.findViewById(R.id.txt2)
    }
}