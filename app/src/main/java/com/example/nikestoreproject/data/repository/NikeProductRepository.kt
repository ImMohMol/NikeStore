package com.example.nikestoreproject.data.repository

import com.example.nikestoreproject.data.models.NikeProduct
import io.reactivex.Completable
import io.reactivex.Single

interface NikeProductRepository {
    fun getNikeProducts(sort: Int): Single<List<NikeProduct>>

    fun getCustomerFavoriteNikeProducts(): Single<List<NikeProduct>>

    fun addNikeProductToCustomerFavorites(): Completable

    fun deleteNikeProductFromCustomerFavorites(): Completable
}