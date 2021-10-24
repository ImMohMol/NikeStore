package com.example.nikestoreproject.common

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class NikeObserver<T>(private val disposables: CompositeDisposable) : SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        this.disposables.add(d)
    }

    override fun onError(e: Throwable) {
        Timber.e(e)
    }
}