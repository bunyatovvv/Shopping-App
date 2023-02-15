package com.example.teststore.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.teststore.R
import com.example.teststore.adapter.CartAdapter
import com.example.teststore.adapter.CartItemClickAdapter
import com.example.teststore.databinding.FragmentBasketBinding
import com.example.teststore.roomdb.ProductEntity
import com.example.teststore.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket), CartItemClickAdapter {

    private var fragmentBinding : FragmentBasketBinding? = null
    private val viewModel : BasketViewModel by viewModels()
    lateinit var cartRecView : RecyclerView
    lateinit var item : ArrayList<ProductEntity>
    lateinit var basketAdapter : CartAdapter
    var sum : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBasketBinding.bind(view)
        fragmentBinding = binding

        cartRecView = binding.cartRecView
        item = arrayListOf()

        binding.bottomCartLayout.visibility = View.GONE
        binding.myBag.visibility = View.GONE
        binding.emptyBagMsgLayout.visibility = View.VISIBLE

        cartRecView.layoutManager = LinearLayoutManager(context)
        basketAdapter = CartAdapter(this)
        cartRecView.adapter = basketAdapter
        observeLiveData()

        fragmentBinding!!.checkOutBagPage.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToPaymenttMethodFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun observeLiveData(){
        viewModel.allProducts.observe(viewLifecycleOwner, Observer {
            it?.let{
                basketAdapter.updateList(it)
                item.clear()
                sum = 0
                item.addAll(it)
            }

            if (it.size == 0){
                with(fragmentBinding!!){
                    bottomCartLayout.visibility = View.GONE
                    myBag.visibility = View.GONE
                    emptyBagMsgLayout.visibility = View.VISIBLE
                }
            } else {
                with(fragmentBinding!!) {
                    emptyBagMsgLayout.visibility = View.GONE
                    bottomCartLayout.visibility = View.VISIBLE
                    myBag.visibility = View.VISIBLE
                }

                item.forEach {
                    sum += it.price
                }
                fragmentBinding!!.totalPriceBagFrag.text = "$" + sum
            }
        })
    }

    override fun onItemDeleteClick(product: ProductEntity) {
        viewModel.deleteCart(product)
    }

    override fun onItemUpdateClick(product: ProductEntity) {
        viewModel.updateCart(product)
    }
}