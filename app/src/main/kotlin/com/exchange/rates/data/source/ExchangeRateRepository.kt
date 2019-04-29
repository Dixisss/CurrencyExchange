package com.exchange.rates.data.source

import com.exchange.rates.data.model.Rates
import io.reactivex.Maybe
import io.reactivex.Single

interface ExchangeRateRepository {

    fun getRates(date: Long): Single<Rates>

    fun getLastUpdateDate(): Maybe<Rates>
}