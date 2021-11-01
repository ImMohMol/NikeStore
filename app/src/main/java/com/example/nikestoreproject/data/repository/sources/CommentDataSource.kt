package com.example.nikestoreproject.data.repository.sources

import com.example.nikestoreproject.data.models.Comment
import io.reactivex.Single

interface CommentDataSource {
    fun getAll(productId: Int): Single<List<Comment>>

    fun insert(): Comment
}