package com.exchange.rates.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<View : MvpView>(var compositeDisposable: CompositeDisposable) : MvpPresenter<View>() {

    fun unsubscribeOnDestroy(subscription: Disposable) {
        compositeDisposable.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}