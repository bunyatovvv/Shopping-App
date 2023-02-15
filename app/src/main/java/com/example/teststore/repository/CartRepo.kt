package com.example.teststore.repository

import androidx.lifecycle.LiveData
import com.example.teststore.api.RetrofitApi
import com.example.teststore.roomdb.ProductDao
import com.example.teststore.roomdb.ProductEntity
import javax.inject.Inject

class CartRepo @Inject constructor(
    private val productDao: ProductDao,
    private val retrofit : RetrofitApi
) {

    suspend fun getCoverProducts() = retrofit.getCoverProducts()

    suspend fun getNewProducts() = retrofit.getNewProducts()

    suspend fun getSaleProducts() = retrofit.getSaleProducts()

    val allCartProducts : LiveData<List<ProductEntity>> = productDao.getAll()

    suspend fun insert(product : ProductEntity){
        productDao.insert(product)
    }

    suspend fun delete(product: ProductEntity){
        productDao.delete(product)
    }

    suspend fun update(product: ProductEntity){
        productDao.update(product)
    }
}