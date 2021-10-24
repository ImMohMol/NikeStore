package com.example.nikestoreproject.data.repository.implementations

import com.example.nikestoreproject.data.models.Banner
import com.example.nikestoreproject.data.repository.BannerRepository
import com.example.nikestoreproject.data.repository.sources.BannerDataSource
import io.reactivex.Single

class BannerRepositoryImplementation(private val bannerDataSource: BannerDataSource) :
    BannerRepository {
    override fun getBanners(): Single<List<Banner>> = this.bannerDataSource.getBanners()
}