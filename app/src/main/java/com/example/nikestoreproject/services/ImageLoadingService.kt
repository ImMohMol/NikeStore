package com.example.nikestoreproject.services

import com.example.nikestoreproject.view.NikeImageView

interface ImageLoadingService {
    fun loadImage(imageView: NikeImageView, imageUrl: String)
}