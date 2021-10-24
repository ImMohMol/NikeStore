package com.example.nikestoreproject.nikeFeature.main

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestoreproject.R
import com.example.nikestoreproject.common.formatPrice
import com.example.nikestoreproject.common.implementSpringAnimationTrait
import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.services.ImageLoadingService
import com.example.nikestoreproject.view.NikeImageView

class ProductAdapter(private val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productList = ArrayList<NikeProduct>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    inner class ProductViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: NikeImageView = itemView.findViewById(R.id.ivProductImage)
        private val previousPrice: TextView = itemView.findViewById(R.id.tvPreviousPrice)
        private val currentPrice: TextView = itemView.findViewById(R.id.tvCurrentPrice)
        private val favorite: ImageView = itemView.findViewById(R.id.ivFavoriteButton)
        private val title: TextView = itemView.findViewById(R.id.tvProductTitle)

        @SuppressLint("SetTextI18n")
        fun bindProductInformation(productItem: NikeProduct) {
            imageLoadingService.loadImage(this.productImage, productItem.image)
            this.title.text = productItem.title
            this.currentPrice.text = formatPrice(productItem.price)
            this.previousPrice.text = formatPrice(productItem.previous_price)
            this.previousPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            this.itemView.implementSpringAnimationTrait()
            this.itemView.setOnClickListener {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProductInformation(this.productList[position])
    }

    override fun getItemCount(): Int = this.productList.size
}