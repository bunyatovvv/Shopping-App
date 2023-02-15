package com.example.teststore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststore.model.Product
import com.example.teststore.model.ProductResponse
import com.example.teststore.repository.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository : CartRepo
) : ViewModel() {

    init {
        getAllProducts()
    }

    private var _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
        get() = _products

    private val _responseCover = MutableLiveData<ProductResponse>()
    val productsListCover : LiveData<ProductResponse>
        get() = _responseCover

    private fun getAllProducts() = viewModelScope.launch {
        val response = repository.getCoverProducts()
        if (response.isSuccessful){
            _responseCover.postValue(response.body())
        }
    }
}