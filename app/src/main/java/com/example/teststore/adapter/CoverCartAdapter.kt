package com.example.teststore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teststore.R
import com.example.teststore.model.Product
import com.example.teststore.ui.CartFragmentDirections
import com.example.teststore.ui.HomeFragmentDirections
import com.example.teststore.util.downloadFromUrl
import com.example.teststore.util.placeHolderProgressBar

class CoverCartAdapter : RecyclerView.Adapter<CoverCartAdapter.CoverViewHolder> (){
    class CoverViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    private val diffUtil = object  : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerDiffer = AsyncListDiffer(this,diffUtil)
    var produts : List<Product>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cover_single,parent,false)
        return CoverViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        val productList = produts.get(position)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.productImage_coverPage)
        val titleText = holder.itemView.findViewById<TextView>(R.id.productNoteCover)
        val button = holder.itemView.findViewById<Button>(R.id.productCheck_coverPage)

        titleText.text = productList.productNote
        // priceText.text = "${productList.productPrice.toString().toFloat()}$"
        imageView.downloadFromUrl(productList.productImage, placeHolderProgressBar(holder.itemView.context))
        button.setOnClickListener {
            val action = CartFragmentDirections.actionCartFragmentToProductDetailsFragment(productList)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return  produts.size
    }
}