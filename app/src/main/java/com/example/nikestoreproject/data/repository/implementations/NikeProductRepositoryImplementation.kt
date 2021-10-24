package com.example.nikestoreproject.data.repository.implementations

import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.data.repository.NikeProductRepository
import com.example.nikestoreproject.data.repository.sources.NikeProductDataSource
import io.reactivex.Completable
import io.reactivex.Single

class NikeProductRepositoryImplementation(
    private val nikeProductRemoteDataSource: NikeProductDataSource,
    private val nikeProductLocalDataSource: NikeProductDataSource
) : NikeProductRepository {
    override fun getNikeProducts(sort: Int): Single<List<NikeProduct>> =
        this.nikeProductRemoteDataSource.getNikeProducts(sort)

    override fun getCustomerFavoriteNikeProducts(): Single<List<NikeProduct>> {
        TODO("Not yet implemented")
    }

    override fun addNikeProductToCustomerFavorites(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteNikeProductFromCustomerFavorites(): Completable {
        TODO("Not yet implemented")
    }
}