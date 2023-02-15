package com.example.teststore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.teststore.R
import com.example.teststore.databinding.FragmentHeartBinding

class HeartFragment : Fragment(R.layout.fragment_heart) {

    private var fragmentBinding : FragmentHeartBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHeartBinding.bind(view)
        fragmentBinding = binding
    }
}