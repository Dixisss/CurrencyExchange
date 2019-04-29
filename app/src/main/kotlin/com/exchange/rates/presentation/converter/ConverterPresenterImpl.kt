package com.exchange.rates.presentation.converter

import com.arellomobile.mvp.InjectViewState
import com.exchange.rates.data.model.Rates
import com.exchange.rates.data.source.ExchangeRateRepository
import com.exchange.rates.presentation.detail.ConverterPresenter
import com.exchange.rates.presentation.detail.ConverterView
import com.exchange.rates.util.AppSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class ConverterPresenterImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val repository: ExchangeRateRepository,
    private val schedulers: AppSchedulers,
    private val updateIntervalMillis: Long
)
    : ConverterPresenter(compositeDisposable) {

    override fun onFirstViewAttach() {
        Timber.e("onFirstViewAttach")
    }

    override fun attachView(view: ConverterView?) {
        Timber.e("attachView")
        super.attachView(view)
        clearCompositeDisposable()
        load()
    }

    override fun currencyFromSelected(currency: Currency) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currencyToSelected(currency: Currency) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSelectionTo(currency: Currency) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun amountChanged(amount: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onExchangeClicked() {
        viewState.openDetailScreen()
    }

    public fun load() {
        compositeDisposable.add(
            repository
                .getLastUpdateDate()
                .map { t ->
                    when (val diff = System.currentTimeMillis() - t.last_update){
                        in 1 until updateIntervalMillis -> InitRates(diff, t)
                        else -> InitRates(0L)
                    }
                }
                .defaultIfEmpty(InitRates(0L))
                .flatMapObservable { t ->
                    val result = Observable
                        .interval(t.diff, updateIntervalMillis, TimeUnit.MILLISECONDS)
                        .flatMap {
                            repository
                                .getRates(System.currentTimeMillis())
                                .toObservable()
                        }
                    t.value?.run {
                        result.startWith(t.value)
                    }
                    result
                }
                .subscribeOn(schedulers.ioThread)
                .observeOn(schedulers.mainThread)
                .subscribe(
                    { rates -> onDataLoaded(rates) },
                    { error -> onError(error) }
                )
            )
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }

    private fun onDataLoaded(rates: Rates) {

    }

    private fun onError(e: Throwable) {
        Timber.e(e)
    }

    data class InitRates (
        val diff: Long,
        val value: Rates? = null
    )
}