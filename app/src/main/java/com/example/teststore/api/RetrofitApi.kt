package com.example.teststore.api

import com.example.teststore.model.Product
import com.example.teststore.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("bunyatovvv/newApi/main/CoverProducts.json")
    suspend fun getCoverProducts() : Response<ProductResponse>

    @GET("bunyatovvv/newApi/main/NewProducts.json")
    suspend fun getNewProducts() : Response<ProductResponse>

    @GET("bunyatovvv/newApi/main/newApii.json")
    suspend fun getSaleProducts() : Response<ProductResponse>
}