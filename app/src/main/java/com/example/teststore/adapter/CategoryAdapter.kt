package com.example.teststore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teststore.R
import com.example.teststore.model.Category
import com.example.teststore.ui.ProductDetailsFragment
import org.w3c.dom.Text

class CategoryAdapter(val categoryList : ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()
{
    class CategoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_single,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item : Category = categoryList[position]

        val categoryName = holder.itemView.findViewById<TextView>(R.id.categoryName)
        val categoryImage = holder.itemView.findViewById<ImageView>(R.id.categoryImage)

        categoryName.text = item.name
        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(categoryImage)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }
}