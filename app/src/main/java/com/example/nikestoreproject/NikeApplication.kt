package com.example.nikestoreproject

import android.app.Application
import android.os.Bundle
import com.example.nikestoreproject.data.repository.BannerRepository
import com.example.nikestoreproject.data.repository.CommentRepository
import com.example.nikestoreproject.data.repository.NikeProductRepository
import com.example.nikestoreproject.data.repository.implementations.BannerRepositoryImplementation
import com.example.nikestoreproject.data.repository.implementations.CommentRepositoryImplementation
import com.example.nikestoreproject.data.repository.implementations.NikeProductRepositoryImplementation
import com.example.nikestoreproject.data.repository.sourcesImplementations.BannerRemoteDataSource
import com.example.nikestoreproject.data.repository.sourcesImplementations.CommentRemoteDataSource
import com.example.nikestoreproject.data.repository.sourcesImplementations.NikeProductLocalDataSource
import com.example.nikestoreproject.data.repository.sourcesImplementations.NikeProductRemoteDataSource
import com.example.nikestoreproject.nikeFeature.main.MainViewModel
import com.example.nikestoreproject.nikeFeature.main.ProductAdapter
import com.example.nikestoreproject.nikeFeature.productDetails.ProductDetailViewModel
import com.example.nikestoreproject.services.ImageLoadingService
import com.example.nikestoreproject.services.getApiServiceImplementation
import com.example.nikestoreproject.services.implementations.FrescoImageLoadingService
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class NikeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)

        val myModules = module {
            single { getApiServiceImplementation() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            factory<NikeProductRepository> {
                NikeProductRepositoryImplementation(
                    NikeProductRemoteDataSource(get()),
                    NikeProductLocalDataSource()
                )

            }
            factory { ProductAdapter(get()) }
            factory<BannerRepository> { BannerRepositoryImplementation(BannerRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImplementation(CommentRemoteDataSource(get())) }
            viewModel { MainViewModel(get(), get()) }
            viewModel { (bundle: Bundle) -> ProductDetailViewModel(bundle, get()) }
        }

        startKoin {
            androidContext(this@NikeApplication)
            modules(myModules)
        }
    }
}