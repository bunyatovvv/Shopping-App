package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.teststore.R
import com.example.teststore.databinding.FragmentShippingAdressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShippingAdressFragment : Fragment(R.layout.fragment_shipping_adress) {

    private var fragmentBinding : FragmentShippingAdressBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShippingAdressBinding.bind(view)
        fragmentBinding = binding

        binding.backImg.setOnClickListener {
            if (getFragmentManager()?.getBackStackEntryCount() != 0) {
                getFragmentManager()?.popBackStack();
            }
        }
    }
}