package com.example.nikestoreproject.nikeFeature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nikestoreproject.R
import com.example.nikestoreproject.common.EXTRA_KEY_DATA
import com.example.nikestoreproject.data.models.Banner
import com.example.nikestoreproject.services.ImageLoadingService
import com.example.nikestoreproject.view.NikeImageView
import org.koin.android.ext.android.inject

class BannerFragment : Fragment() {
    val imageLoadingService: ImageLoadingService by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var imageView =
            inflater.inflate(R.layout.banner_fragment, container, false) as NikeImageView
        val banner =
            requireArguments().getParcelable<Banner>(EXTRA_KEY_DATA) ?: throw IllegalStateException(
                "Banner cannot be null"
            )
        this.imageLoadingService.loadImage(imageView, banner.image)
        return imageView
    }

    companion object {
        fun newInstance(banner: Banner): BannerFragment {
            return BannerFragment().apply {
                this.arguments = Bundle().apply {
                    this.putParcelable(EXTRA_KEY_DATA, banner)
                }
            }
        }
    }
}