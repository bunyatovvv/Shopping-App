package com.example.teststore.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teststore.R
import com.example.teststore.adapter.CoverProductAdapter
import com.example.teststore.adapter.NewProductAdapter
import com.example.teststore.adapter.SaleProductAdapter
import com.example.teststore.databinding.FragmentHomeBinding
import com.example.teststore.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val coverProductAdapter by lazy { CoverProductAdapter() }
    private val newProductAdapter by lazy { NewProductAdapter() }
    private val saleProductAdapter by lazy { SaleProductAdapter() }


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()

        binding.coverRecView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.newRecView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.saleRecView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.coverRecView.setHasFixedSize(true)

    }

    private fun observeLiveData() {
        with(binding){
            viewModel.productsListCover.observe(viewLifecycleOwner, Observer {
                coverProductAdapter.produts = it
                coverRecView.adapter = coverProductAdapter
            })

            viewModel.productsListNew.observe(viewLifecycleOwner, Observer {
                newProductAdapter.produts = it
                newRecView.adapter = newProductAdapter
            })

            viewModel.productsListSale.observe(viewLifecycleOwner, Observer {
                saleProductAdapter.produts = it
                saleRecView.adapter = saleProductAdapter
            })
        }
    }
}