package com.example.nikestoreproject.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikestoreproject.R
import io.reactivex.disposables.CompositeDisposable

abstract class NikeFragment : Fragment(), NikeView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context
}

abstract class NikeActivity : AppCompatActivity(), NikeView {
    override val rootView: CoordinatorLayout?
        get() = window.decorView.rootView as CoordinatorLayout
    override val viewContext: Context?
        get() = this
}

interface NikeView {
    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun showLoading(mustShow: Boolean) {
        rootView?.let {
            viewContext?.let { context ->
                var loadingViewHandler = it.findViewById<View>(R.id.loadingView)
                if (loadingViewHandler == null && mustShow) {
                    loadingViewHandler =
                        LayoutInflater.from(context).inflate(R.layout.loading_view, it, false)
                    it.addView(loadingViewHandler)
                }
                loadingViewHandler?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}

abstract class NikeViewModel : ViewModel() {
    val disposables = CompositeDisposable()
    val loadingLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        this.disposables.clear()
        super.onCleared()
    }
}