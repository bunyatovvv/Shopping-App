package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.teststore.R
import com.example.teststore.databinding.FragmentAddShippingAdressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddShippingAdress : Fragment(R.layout.fragment_add_shipping_adress) {

    private var fragmentBinding : FragmentAddShippingAdressBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddShippingAdressBinding.bind(view)
        fragmentBinding = binding

    }
}