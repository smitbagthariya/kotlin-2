package com.example.startquiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class DashboardAdapter(private val context: Context, private val items: List<Model>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.txt1)
        val imageView = view.findViewById<ImageView>(R.id.img1)

        val item = items[position]
        textView.text = item.title1
        imageView.setImageResource(item.image)

        return view
    }
}
