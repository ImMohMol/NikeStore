package com.example.nikestoreproject.nikeFeature.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestoreproject.R
import com.example.nikestoreproject.common.EXTRA_KEY_DATA
import com.example.nikestoreproject.common.NikeFragment
import com.example.nikestoreproject.common.convertPxToDp
import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.nikeFeature.productDetails.ProductDetailActivity
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment : NikeFragment(), ProductAdapter.OnProductClickListener {
    private val mainViewModel: MainViewModel by viewModel()
    private val latestProductAdapter: ProductAdapter by inject()
    private val bestSellingProductAdapter: ProductAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestProductAdapter.productClickListener = this
        bestSellingProductAdapter.productClickListener = this

        mainFragment_rvLatestProducts.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        mainFragment_rvLatestProducts.adapter = latestProductAdapter

        mainFragment_rvBestSellingProducts.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        mainFragment_rvBestSellingProducts.adapter = bestSellingProductAdapter

        this.mainViewModel.nikeProductLiveData.observe(viewLifecycleOwner) {
            latestProductAdapter.productList = it as ArrayList<NikeProduct>
        }

        this.mainViewModel.nikeBestSellingProductsLiveData.observe(viewLifecycleOwner, {
            bestSellingProductAdapter.productList = it as ArrayList<NikeProduct>
        })

        this.mainViewModel.loadingLiveData.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        this.mainViewModel.bannerLiveData.observe(viewLifecycleOwner, {
            Timber.i(it.toString())
            val bannerSliderAdapter = BannerSliderAdapter(this, it)
            bannerSliderViewPager.adapter = bannerSliderAdapter
            val viewPagerHeight = (((bannerSliderViewPager.measuredWidth - convertPxToDp(
                32f,
                requireContext()
            )) * 173) / 328).toInt()
            val layoutParameters = bannerSliderViewPager.layoutParams
            layoutParameters.height = viewPagerHeight
            bannerSliderViewPager.layoutParams = layoutParameters

            sliderIndicator.setViewPager2(bannerSliderViewPager)
        })
    }

    override fun onProductItemClicked(selectedProduct: NikeProduct) {
        startActivity(Intent(requireContext(), ProductDetailActivity::class.java).apply {
            this.putExtra(EXTRA_KEY_DATA, selectedProduct)
        })

    }
}