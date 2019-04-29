package com.exchange.rates.data.source

import com.exchange.rates.data.model.Rates
import io.reactivex.Completable
import io.reactivex.Maybe

interface ExchangeRateDataSource {

    fun getRates(date: Long): Maybe<Rates>

    fun getLastRate(): Maybe<Rates>

    fun createOrUpdateRates(rates: Rates): Completable

    fun deleteRates(): Completable
}