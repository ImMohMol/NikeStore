package com.example.nikestoreproject.data.repository.sourcesImplementations

import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.data.repository.sources.NikeProductDataSource
import com.example.nikestoreproject.services.IApiService
import io.reactivex.Completable
import io.reactivex.Single

class NikeProductRemoteDataSource(private val apiService: IApiService) : NikeProductDataSource {
    override fun getNikeProducts(sort: Int): Single<List<NikeProduct>> =
        this.apiService.getNikeProducts(sort.toString())

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