package com.example.nikestoreproject.nikeFeature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nikestoreproject.data.models.Banner

class BannerSliderAdapter(fragment: Fragment, private val banners: List<Banner>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = this.banners.size

    override fun createFragment(position: Int): Fragment =
        BannerFragment.newInstance(this.banners[position])
}