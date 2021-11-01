package com.example.nikestoreproject.nikeFeature.productDetails

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.nikestoreproject.common.EXTRA_KEY_DATA
import com.example.nikestoreproject.common.NikeObserver
import com.example.nikestoreproject.common.NikeViewModel
import com.example.nikestoreproject.data.models.Comment
import com.example.nikestoreproject.data.models.NikeProduct
import com.example.nikestoreproject.data.repository.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(bundle: Bundle, private val commentRepository: CommentRepository) :
    NikeViewModel() {
    var productLiveData = MutableLiveData<NikeProduct>()
    var productCommentsLiveData = MutableLiveData<List<Comment>>()

    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        this.commentRepository.getAll(this.productLiveData.value!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeObserver<List<Comment>>(disposables) {
                override fun onSuccess(t: List<Comment>) {
                    productCommentsLiveData.value = t
                }
            })
    }
}