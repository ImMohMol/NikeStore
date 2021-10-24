package com.example.nikestoreproject.data.repository.sources

import com.example.nikestoreproject.data.models.Banner
import io.reactivex.Single

interface BannerDataSource {
    fun getBanners(): Single<List<Banner>>
}