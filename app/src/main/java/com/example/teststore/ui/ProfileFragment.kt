package com.example.teststore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.teststore.R
import com.example.teststore.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var fragmentBinding : FragmentProfileBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProfileBinding.bind(view)
        fragmentBinding = binding

        binding.shippingAdressProfile.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToShippingAdressFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}