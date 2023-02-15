package com.example.teststore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststore.model.Product
import com.example.teststore.repository.CartRepo
import com.example.teststore.roomdb.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: CartRepo
) : ViewModel() {

    val allProducts : LiveData<List<ProductEntity>>

    init {
        allProducts = repository.allCartProducts
    }

    fun insert(product : ProductEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(product)
    }

    fun deleteCart(product : ProductEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(product)
    }

    fun updateCart(product : ProductEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.update(product)
    }
}