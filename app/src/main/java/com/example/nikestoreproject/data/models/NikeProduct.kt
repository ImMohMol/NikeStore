package com.example.nikestoreproject.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NikeProduct(
    val discount: Int,
    val id: Int,
    val image: String,
    val previous_price: Int,
    val price: Int,
    val status: Int,
    val title: String
) : Parcelable

// VARIABLES FOR SORT TYPE FOR RECEIVING ITEMS FROM SERVER
const val SORT_BEST_SELLING_PRODUCTS = 1
const val SORT_PRICE_DESC_PRODUCTS = 2
const val SORT_PRICE_ASC_PRODUCTS = 3
const val SORT_LATEST_PRODUCTS = 0
