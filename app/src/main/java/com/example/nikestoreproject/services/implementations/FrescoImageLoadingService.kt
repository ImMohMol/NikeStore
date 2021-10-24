package com.example.nikestoreproject.services.implementations

import com.example.nikestoreproject.services.ImageLoadingService
import com.example.nikestoreproject.view.NikeImageView

class FrescoImageLoadingService: ImageLoadingService {
    override fun loadImage(imageView: NikeImageView, imageUrl: String) {
        imageView.setImageURI(imageUrl)
    }
}