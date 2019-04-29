package com.exchange.rates.di.modules

import com.exchange.rates.data.source.ExchangeRateRepository
import com.exchange.rates.di.annotations.UpdateIntervalMillis
import com.exchange.rates.presentation.converter.ConverterFragment
import com.exchange.rates.presentation.converter.ConverterPresenterImpl
import com.exchange.rates.presentation.detail.ConverterPresenter
import com.exchange.rates.presentation.detail.ConverterView
import com.exchange.rates.util.AppSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ConverterFragmentModule {

    @Provides
    @UpdateIntervalMillis
    fun provideUpdateIntervalMillis(): Long = 30 * 1000 // 30s

    @Provides
    fun provideConverterView(converterFragment: ConverterFragment): ConverterView =
        converterFragment

    @Provides
    fun provideConverterPresenter(compositeDisposable: CompositeDisposable,
                                  appSchedulers: AppSchedulers,
                                  repository: ExchangeRateRepository,
                                  @UpdateIntervalMillis interval: Long): ConverterPresenter =
        ConverterPresenterImpl(compositeDisposable, repository, appSchedulers, interval)
}