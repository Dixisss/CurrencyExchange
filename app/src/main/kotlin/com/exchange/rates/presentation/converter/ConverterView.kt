package com.exchange.rates.presentation.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import java.util.*

@StateStrategyType(AddToEndSingleStrategy::class)
interface ConverterView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen()

    fun setFromCurrency(currency: Currency)

    fun setToCurrency(currency: Currency)

    fun conversionResult(result: String)

    fun showNetworkNotAvailableError()

    fun showError()
}