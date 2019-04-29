package com.exchange.rates.presentation.detail

import com.arellomobile.mvp.InjectViewState
import com.exchange.rates.presentation.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import java.util.*

@InjectViewState
abstract class ConverterPresenter(compositeDisposable: CompositeDisposable) : BasePresenter<ConverterView>(compositeDisposable) {

    abstract fun currencyFromSelected(currency: Currency)

    abstract fun currencyToSelected(currency: Currency)

    abstract fun setSelectionTo(currency: Currency)

    abstract fun amountChanged(amount: String)

    abstract fun onExchangeClicked()
}