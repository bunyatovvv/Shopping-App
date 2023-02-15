package com.example.teststore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teststore.R
import com.example.teststore.model.Product
import com.example.teststore.ui.HomeFragmentDirections
import com.example.teststore.util.downloadFromUrl
import com.example.teststore.util.placeHolderProgressBar

class SaleProductAdapter : RecyclerView.Adapter<SaleProductAdapter.SaleProductViewHolder>() {

    class SaleProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_product,parent,false)
        return SaleProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleProductViewHolder, position: Int) {
        val productList = produts.get(position)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.productImage_singleProduct)
        val brandName = holder.itemView.findViewById<TextView>(R.id.productBrandName_singleProduct)
        val productName = holder.itemView.findViewById<TextView>(R.id.productName_singleProduct)
        val productPrice = holder.itemView.findViewById<TextView>(R.id.productPrice_singleProduct)
        val rating = holder.itemView.findViewById<RatingBar>(R.id.productRating_singleProduct)
        val discount = holder.itemView.findViewById<TextView>(R.id.discountText_single)
        val discountCardView = holder.itemView.findViewById<CardView>(R.id.discount_cardview_single)

        brandName.text = productList.productBrand
        productName.text = productList.productName
        productPrice.text = "$${productList.productPrice}"
        rating.rating = productList.productRating.toFloat()

        if (!productList.productDisCount.isNullOrEmpty()){
            discountCardView.visibility = View.VISIBLE
            discount.text = "-${productList.productDisCount}"
        } else{
            discountCardView.visibility = View.VISIBLE
            discount.text = "New"
        }

        // priceText.text = "${productList.productPrice.toString().toFloat()}$"
        imageView.downloadFromUrl(productList.productImage, placeHolderProgressBar(holder.itemView.context))
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(productList)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return  produts.size
    }
}