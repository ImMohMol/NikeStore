package com.example.nikestoreproject.nikeFeature.productDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestoreproject.R
import com.example.nikestoreproject.data.models.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    var comments = ArrayList<Comment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentContent = this.itemView.findViewById<TextView>(R.id.commentContent)
        private val commentAuthor = this.itemView.findViewById<TextView>(R.id.commentAuthor)
        private val commentTitle = this.itemView.findViewById<TextView>(R.id.commentTitle)
        private val commentDate = this.itemView.findViewById<TextView>(R.id.commentDate)

        fun bindComment(comment: Comment) {
            this.commentAuthor.text = comment.author.email
            this.commentContent.text = comment.content
            this.commentTitle.text = comment.title
            this.commentDate.text = comment.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_rv_item, parent, false)
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindComment(this.comments[position])
    }

    override fun getItemCount(): Int {
        return if (comments.size > 3) 3 else this.comments.size
    }
}