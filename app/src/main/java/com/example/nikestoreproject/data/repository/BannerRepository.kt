package com.example.nikestoreproject.data.repository

import com.example.nikestoreproject.data.models.Banner
import io.reactivex.Single

interface BannerRepository {
    fun getBanners(): Single<List<Banner>>
}