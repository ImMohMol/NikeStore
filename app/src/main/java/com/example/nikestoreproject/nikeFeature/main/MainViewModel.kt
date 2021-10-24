package com.example.nikestoreproject.nikeFeature.main

import androidx.lifecycle.MutableLiveData
import com.example.nikestoreproject.common.NikeObserver
import com.example.nikestoreproject.common.NikeViewModel
import com.example.nikestoreproject.data.models.Banner
import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.data.models.SORT_BEST_SELLING_PRODUCTS
import com.example.nikestoreproject.data.models.SORT_LATEST_PRODUCTS
import com.example.nikestoreproject.data.repository.BannerRepository
import com.example.nikestoreproject.data.repository.NikeProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val nikeProductRepository: NikeProductRepository,
    private val bannerRepository: BannerRepository
) : NikeViewModel() {
    val nikeBestSellingProductsLiveData = MutableLiveData<List<NikeProduct>>()
    val nikeProductLiveData = MutableLiveData<List<NikeProduct>>()
    val bannerLiveData = MutableLiveData<List<Banner>>()

    init {
        this.getLatestProducts()

        this.getBestSellingProducts()

        this.getBanners()
    }

    private fun getLatestProducts() {
        this.loadingLiveData.value = true
        this.nikeProductRepository.getNikeProducts(SORT_LATEST_PRODUCTS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.loadingLiveData.value = false
            }
            .subscribe(object : NikeObserver<List<NikeProduct>>(this.disposables) {
                override fun onSuccess(t: List<NikeProduct>) {
                    nikeProductLiveData.value = t
                }
            })
    }

    private fun getBestSellingProducts() {
        this.loadingLiveData.value = true
        this.nikeProductRepository.getNikeProducts(SORT_BEST_SELLING_PRODUCTS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.loadingLiveData.value = false
            }
            .subscribe(object : NikeObserver<List<NikeProduct>>(this.disposables) {
                override fun onSuccess(t: List<NikeProduct>) {
                    nikeBestSellingProductsLiveData.value = t
                }
            })
    }

    private fun getBanners() {
        this.loadingLiveData.value = true
        this.bannerRepository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.loadingLiveData.value = false
            }
            .subscribe(object : NikeObserver<List<Banner>>(this.disposables) {
                override fun onSuccess(t: List<Banner>) {
                    bannerLiveData.value = t
                }
            })
    }
}