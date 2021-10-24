package com.example.nikestoreproject.data.repository.sources

import com.example.nikestoreproject.data.models.NikeProduct
import io.reactivex.Completable
import io.reactivex.Single

interface NikeProductDataSource {
    fun getNikeProducts(sort: Int): Single<List<NikeProduct>>

    fun getCustomerFavoriteNikeProducts(): Single<List<NikeProduct>>

    fun addNikeProductToCustomerFavorites(): Completable

    fun deleteNikeProductFromCustomerFavorites(): Completable
}