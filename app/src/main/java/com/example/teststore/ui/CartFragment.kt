package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teststore.R
import com.example.teststore.adapter.CategoryAdapter
import com.example.teststore.adapter.CoverCartAdapter
import com.example.teststore.adapter.CoverProductAdapter
import com.example.teststore.databinding.FragmentCartBinding
import com.example.teststore.model.Category
import com.example.teststore.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val coverProductAdapter by lazy { CoverCartAdapter() }
    private var fragmentBinding : FragmentCartBinding? = null
    private val viewModel : CartViewModel by viewModels()
    private lateinit var cateList : ArrayList<Category>
    private var categoryAdapter : CategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cateList = arrayListOf()
        val binding = FragmentCartBinding.bind(view)
        fragmentBinding = binding
        binding.coverRecViewShopFragment.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.coverRecViewShopFragment.setHasFixedSize(true)
        binding.categoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL,false)
        binding.categoriesRecyclerView.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(cateList)
        binding.categoriesRecyclerView.adapter = categoryAdapter

        observeLiveData()
        setCategoryData()
    }

    private fun observeLiveData() {
        with(fragmentBinding!!) {
            viewModel.productsListCover.observe(viewLifecycleOwner, Observer {
                coverProductAdapter.produts = it
                coverRecViewShopFragment.adapter = coverProductAdapter
            })
        }
    }

    private fun setCategoryData() {
        cateList.add(Category("Clothing","https://images.unsplash.com/photo-1434389677669-e08b4cac3105?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=649&q=80"))
        cateList.add(Category("Jewelry","https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80"))
        cateList.add(Category("Hair Accessories","https://images.unsplash.com/photo-1626954079979-ec4f7b05e032?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"))
        cateList.add(Category("Costume Accessories","https://images.unsplash.com/photo-1606760227091-3dd870d97f1d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"))
        cateList.add(Category("Handbag & Wallet Accessories","https://images.unsplash.com/photo-1601924928357-22d3b3abfcfb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"))
        cateList.add(Category("One-Pieces","https://images.unsplash.com/photo-1529171374461-2ea966dee0f5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"))
        cateList.add(Category("Masks","https://images.unsplash.com/photo-1586942593568-29361efcd571?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"))
        cateList.add(Category("Glasses","https://images.unsplash.com/photo-1546180245-c59500ad14d0?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80"))
    }
}