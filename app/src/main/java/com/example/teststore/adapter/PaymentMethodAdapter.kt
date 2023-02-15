package com.example.teststore.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.teststore.R
import com.example.teststore.roomdb.ProductEntity
import com.example.teststore.roomdb.creditcard.CardEntity
import com.example.teststore.roomdb.creditcard.CardViewModel
import com.example.teststore.ui.PaymentMethodFragmentDirections
import com.google.android.material.snackbar.Snackbar
import kotlin.math.exp

class PaymentMethodAdapter(val listener : CardItemClick) : RecyclerView.Adapter<PaymentMethodAdapter.PaymentViewHolder>() {

    private val cardList : ArrayList<CardEntity> = arrayListOf()


    class PaymentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.single_card,parent,false)
        return PaymentViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val cardItem : CardEntity = cardList[position]

        val cardNumber = holder.itemView.findViewById<TextView>(R.id.cardNumber_singleCard)
        val cardHolder = holder.itemView.findViewById<TextView>(R.id.cardHolderName_singleCard)
        val cardExpiryDate = holder.itemView.findViewById<TextView>(R.id.expiryDate_singleCard)

        cardNumber.text = cardItem.number
        cardHolder.text = cardItem.nameCH
        cardExpiryDate.text = cardItem.exp

        holder.itemView.setOnLongClickListener {
            listener.onItemLongClick(cardItem)
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            val action = PaymentMethodFragmentDirections.actionPaymentMethodFragmentToConfirmPaymentFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(newList : List<CardEntity>){
        cardList.clear()
        cardList.addAll(newList)
        notifyDataSetChanged()
    }

}
interface CardItemClick{
    fun onItemLongClick(cardEntity : CardEntity)
}