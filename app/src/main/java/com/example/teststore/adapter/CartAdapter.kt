package com.example.teststore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.teststore.R
import com.example.teststore.roomdb.ProductEntity
import com.example.teststore.viewmodel.BasketViewModel
import org.w3c.dom.Text

class CartAdapter(val listener : CartItemClickAdapter) : RecyclerView.Adapter<CartAdapter.ViewHolder>()
{

    private val cartList : ArrayList<ProductEntity> = arrayListOf()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cartView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_single,parent,false)
        return ViewHolder(cartView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem : ProductEntity = cartList[position]

        val cartName = holder.itemView.findViewById<TextView>(R.id.cartName)
        val cartPrice = holder.itemView.findViewById<TextView>(R.id.cartPrice)
        val quantity = holder.itemView.findViewById<TextView>(R.id.quantityTvCart)
        val cartImage = holder.itemView.findViewById<ImageView>(R.id.cartImage)
        val cartMore: ImageView = holder.itemView.findViewById(R.id.cartMore)
        val minusLayout = holder.itemView.findViewById<LinearLayout>(R.id.minusLayout)
        val plusLayout = holder.itemView.findViewById<LinearLayout>(R.id.plusLayout)

        cartName.text = cartItem.name
        cartPrice.text = "$${cartItem.price}"
        quantity.text = cartItem.qua.toString()

        cartMore.setOnClickListener {
            listener.onItemDeleteClick(cartItem)
        }


        minusLayout.setOnClickListener {
            if (cartItem.qua>1){
                cartItem.qua--
                quantity.text = cartItem.qua.toString()
            }
        }

        plusLayout.setOnClickListener {
            if (cartItem.qua<10){
                cartItem.qua++
                quantity.text = cartItem.qua.toString()
            }
        }

        Glide.with(holder.itemView.context)
            .load(cartItem.Image)
            .into(cartImage)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun updateList(newList : List<ProductEntity>){
        cartList.clear()
        cartList.addAll(newList)
        notifyDataSetChanged()
    }
}

interface CartItemClickAdapter{
    fun onItemDeleteClick(product : ProductEntity)
    fun onItemUpdateClick(product: ProductEntity)
}