package com.example.startquiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val context: Context, private val categories: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.txtCategory)
        private val imageView: ImageView = view.findViewById(R.id.imgCategory)

        fun bind(category: CategoryModel) {
            textView.text = category.name
            imageView.setImageResource(category.image)

            // Click listener for each category item
            itemView.setOnClickListener {
                val intent = Intent(context, QuestionActivity::class.java)

                // Send correct category name
                intent.putExtra("CATEGORY_NAME", category.name)

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}
