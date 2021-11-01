package com.example.nikestoreproject.data.repository.sourcesImplementations

import com.example.nikestoreproject.data.models.Comment
import com.example.nikestoreproject.data.repository.sources.CommentDataSource
import com.example.nikestoreproject.services.IApiService
import io.reactivex.Single

class CommentRemoteDataSource(private val apiService: IApiService): CommentDataSource {
    override fun getAll(productId: Int): Single<List<Comment>> = this.apiService.getComments(productId)

    override fun insert(): Comment {
        TODO("Not yet implemented")
    }
}