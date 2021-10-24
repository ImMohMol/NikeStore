package com.example.nikestoreproject.services

import com.example.nikestoreproject.data.models.Banner
import com.example.nikestoreproject.data.models.NikeProduct
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("product/list")
    fun getNikeProducts(@Query("sort") sort: String): Single<List<NikeProduct>>

    @GET("banner/slider")
    fun getBanners(): Single<List<Banner>>
}

fun getApiServiceImplementation(): IApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(IApiService::class.java)
}