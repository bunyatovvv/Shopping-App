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
class HomeViewModel @Inject constructor(
    private val repository : CartRepo
) : ViewModel() {

    init {
        getAllProducts()
        getNewProducts()
        getSaleProducts()
    }

    private var _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
        get() = _products

    private val _responseCover = MutableLiveData<ProductResponse>()
    val productsListCover : LiveData<ProductResponse>
        get() = _responseCover

    private val _responseNewProduct = MutableLiveData<ProductResponse>()
    val productsListNew : LiveData<ProductResponse>
        get() = _responseNewProduct

    private val _responseSaleProduct = MutableLiveData<ProductResponse>()
    val productsListSale : LiveData<ProductResponse>
        get() = _responseSaleProduct


    private fun getAllProducts() = viewModelScope.launch {
        val response = repository.getCoverProducts()
        if (response.isSuccessful){
            _responseCover.postValue(response.body())
        }
    }

    private fun getNewProducts() = viewModelScope.launch {
        val response = repository.getNewProducts()
        if (response.isSuccessful){
            _responseNewProduct.postValue(response.body())
        }
    }

    private fun getSaleProducts() = viewModelScope.launch {
        val response = repository.getSaleProducts()
        if (response.isSuccessful){
            _responseSaleProduct.postValue(response.body())
        }
    }



    /*
    private fun getNewProducts() = viewModelScope.launch {
        val response = repository.getNewProducts()
        if (response.isSuccessful){
            _newProducts.postValue(response.body())
        }
    }

     */
}