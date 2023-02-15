package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import com.example.teststore.R
import com.example.teststore.databinding.FragmentConfirmPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmPaymentFragment : BottomSheetDialogFragment(R.layout.fragment_confirm_payment) {

    private var fragmentBinding : FragmentConfirmPaymentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConfirmPaymentBinding.bind(view)
        fragmentBinding = binding

        binding.noBtn.setOnClickListener {
            dismiss()
        }
    }
}