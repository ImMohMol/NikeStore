package com.example.nikestoreproject.data.repository.implementations

import com.example.nikestoreproject.data.models.Comment
import com.example.nikestoreproject.data.repository.CommentRepository
import com.example.nikestoreproject.data.repository.sources.CommentDataSource
import io.reactivex.Single

class CommentRepositoryImplementation(private val commentDataSource: CommentDataSource) :
    CommentRepository {
    override fun getAll(productId: Int): Single<List<Comment>> =
        this.commentDataSource.getAll(productId)

    override fun insert(): Comment = this.commentDataSource.insert()
}