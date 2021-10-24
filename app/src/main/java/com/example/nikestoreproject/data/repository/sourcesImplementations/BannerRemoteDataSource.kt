package com.example.nikestoreproject.data.repository.sourcesImplementations

import com.example.nikestoreproject.data.models.Banner
import com.example.nikestoreproject.data.repository.sources.BannerDataSource
import com.example.nikestoreproject.services.IApiService
import io.reactivex.Single

class BannerRemoteDataSource(private val apiService: IApiService) : BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = this.apiService.getBanners()
}