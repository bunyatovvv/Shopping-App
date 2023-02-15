package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.teststore.R
import com.example.teststore.databinding.FragmentAddToBagBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddToBagFragment : BottomSheetDialogFragment(R.layout.fragment_add_to_bag) {

    private var fragmentBinding : FragmentAddToBagBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddToBagBinding.bind(view)
        fragmentBinding = binding

        binding.addToCartBottomSheet.setOnClickListener {

        }
    }
}