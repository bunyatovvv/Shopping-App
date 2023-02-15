package com.example.teststore.ui

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.withResumed
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teststore.R
import com.example.teststore.adapter.CardItemClick
import com.example.teststore.adapter.CartAdapter
import com.example.teststore.adapter.CartItemClickAdapter
import com.example.teststore.adapter.PaymentMethodAdapter
import com.example.teststore.databinding.FragmentPaymentMethodBinding
import com.example.teststore.roomdb.ProductEntity
import com.example.teststore.roomdb.creditcard.CardEntity
import com.example.teststore.roomdb.creditcard.CardViewModel
import com.example.teststore.viewmodel.BasketViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PaymentMethodFragment : DialogFragment(R.layout.fragment_payment_method),
    CardItemClick {

    private val viewModel : CardViewModel by viewModels()
    lateinit var cardRecView : RecyclerView
    lateinit var item : ArrayList<CardEntity>
    lateinit var paymentAdapter : PaymentMethodAdapter
    var fragmentBinding : FragmentPaymentMethodBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = FragmentPaymentMethodBinding.bind(view)
        fragmentBinding = binding

        cardRecView = binding.cardRecViewPaymentMethodPage
        item = arrayListOf()

        cardRecView.layoutManager = LinearLayoutManager(requireContext())
        paymentAdapter = PaymentMethodAdapter(this)
        cardRecView.adapter = paymentAdapter

        binding.addCardPaymentMethodPage.setOnClickListener {
            val action = PaymentMethodFragmentDirections.actionPaymenttMethodFragmentToAddPaymentFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.allCards.observe(viewLifecycleOwner, Observer {
            it?.let {
                paymentAdapter.updateList(it)
            }
        })
    }

    override fun onItemLongClick(cardEntity: CardEntity) {
        val snack = Snackbar.make(requireView(), "Kartı silmək istəyirsiniz?", Snackbar.LENGTH_LONG)
        snack.setAction("Kartı sil") {
            viewModel.delete(cardEntity)
        }
        snack.show()
    }
}