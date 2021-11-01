package com.example.nikestoreproject.data.repository

import com.example.nikestoreproject.data.models.Comment
import io.reactivex.Single

interface CommentRepository {
    fun getAll(productId: Int): Single<List<Comment>>

    fun insert(): Comment
}