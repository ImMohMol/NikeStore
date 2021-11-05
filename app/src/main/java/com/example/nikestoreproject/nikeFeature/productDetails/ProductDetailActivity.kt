package com.example.nikestoreproject.nikeFeature.productDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestoreproject.R
import com.example.nikestoreproject.common.formatPrice
import com.example.nikestoreproject.data.models.Comment
import com.example.nikestoreproject.services.ImageLoadingService
import com.example.nikestoreproject.view.scroll.ObservableScrollViewCallbacks
import com.example.nikestoreproject.view.scroll.ScrollState
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ProductDetailActivity : AppCompatActivity() {
    private val productDetailViewModel: ProductDetailViewModel by viewModel { parametersOf(intent.extras) }
    private val imageLoadingService: ImageLoadingService by inject()
    private val adapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        showSelectedProductInformation()

        setScrollingAnimation()

        getComments()
    }

    private fun setScrollingAnimation() {
        val toolbar = productDetailsToolBarView
        val productImageView = productDetailsProductImage

        productDetailsProductImage.post {
            productDetailsObservableScrollView.addScrollViewCallbacks(object :
                ObservableScrollViewCallbacks {
                override fun onScrollChanged(
                    scrollY: Int,
                    firstScroll: Boolean,
                    dragging: Boolean
                ) {
                    toolbar.alpha = scrollY.toFloat() / productDetailsProductImage.height
                    productImageView.translationY = scrollY.toFloat() / 2
                }

                override fun onDownMotionEvent() {
                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
                }
            })
        }
    }

    private fun showSelectedProductInformation() {
        this.productDetailViewModel.productLiveData.observe(this, {
            imageLoadingService.loadImage(productDetailsProductImage, it.image)
            ProductDetailsProductTitle.text = it.title
            productDetailsPreviousPrice.text = formatPrice(it.previous_price)
            productDetailsCurrentPrice.text = formatPrice(it.price)
            productDetailsToolBarTitle.text = it.title
        })
    }

    private fun getComments() {
        this.productDetailViewModel.productCommentsLiveData.observe(this, {
            Timber.i(it.toString())
            this.adapter.comments = it as ArrayList<Comment>
            if (it.size > 3)
                productDetailsSeeAllCommentsButton.visibility = View.VISIBLE
            this.initializeCommentsRecyclerView()
        })
    }

    private fun initializeCommentsRecyclerView() {
        commentsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        commentsRv.adapter = this.adapter
    }
}