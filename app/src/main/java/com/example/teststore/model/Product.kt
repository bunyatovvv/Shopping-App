package com.example.teststore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product (
    val productName : String = "",
    val productId : Int = 0,
    val productPrice : Int = 0,
    val productDes : String = "",
    val productRating : Double = 0.0,
    val productDisCount : String? = "",
    val productHave : Boolean = true,
    val productBrand : String = "",
    val productImage : String = "",
    val productCategory : String = "",
    val productNote : String = ""
        ) : Parcelable