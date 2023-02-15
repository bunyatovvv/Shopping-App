package com.example.teststore.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.teststore.R
import com.example.teststore.databinding.FragmentAddPaymentBinding
import com.example.teststore.roomdb.creditcard.CardEntity
import com.example.teststore.roomdb.creditcard.CardViewModel
import com.example.teststore.util.ExpiryDateFormatWatcher
import com.example.teststore.util.FourDigitCardFormatWatcher
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPaymentFragment : DialogFragment(R.layout.fragment_add_payment) {

    var fragmentBinding : FragmentAddPaymentBinding? = null
    private val viewModel : CardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddPaymentBinding.bind(view)
        fragmentBinding = binding
        binding.cardNumberEditText.addTextChangedListener(FourDigitCardFormatWatcher())
        binding.cardExpEditText.
         addTextChangedListener(ExpiryDateFormatWatcher())

        binding.cardSaveBtn.setOnClickListener {
            val number = fragmentBinding!!.cardNumberEditText.text.toString()
            val name = fragmentBinding!!.cardNameEditText.text.toString()
            val expirydate = fragmentBinding!!.cardExpEditText.text.toString()
            val cvv = fragmentBinding!!.cardCvvEditText.text.toString()

            if (ok() && expDate()){
                val card = CardEntity(name,number,expirydate,cvv,"")
                addPaymentMethod(card)
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.getWindow()?.setLayout(width, height)
        }
    }

    private fun addPaymentMethod(card: CardEntity) {
        viewModel.insert(card)
    }

    private fun ok() : Boolean{
        return with(fragmentBinding!!){
            cardNameEditText.text.toString().length > 5
                    && cardExpEditText.text.toString().length == 5
                    && cardCvvEditText.text.toString().length == 3
                    && cardNumberEditText.text.toString().length == 19
        }
    }

    private fun expDate() : Boolean{
        val expirydate = fragmentBinding!!.cardExpEditText.text.toString()
        val monthFirst = expirydate[0].toString()
        val monthSecond = expirydate[1].toString()
        val month = monthFirst + monthSecond

        val yearFirst = expirydate[3].toString()
        val yearSecond = expirydate[4].toString()
        val year = yearFirst + yearSecond

        return (month.toInt() <= 12 && year.toInt() > 22 && year.toInt() < 40)
    }
}