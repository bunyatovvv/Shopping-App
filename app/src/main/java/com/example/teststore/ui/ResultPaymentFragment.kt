package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.teststore.R
import com.example.teststore.databinding.FragmentResultPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultPaymentFragment : DialogFragment(R.layout.fragment_result_payment) {

    private var fragmentBinding : FragmentResultPaymentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentResultPaymentBinding.bind(view)
        fragmentBinding = binding
    }
}