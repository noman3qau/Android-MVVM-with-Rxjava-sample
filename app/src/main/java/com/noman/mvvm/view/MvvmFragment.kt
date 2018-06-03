package com.noman.mvvm.view

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class MvvmFragment : Fragment() {

    val subscription = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        subscription.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subscription.clear()
    }

}