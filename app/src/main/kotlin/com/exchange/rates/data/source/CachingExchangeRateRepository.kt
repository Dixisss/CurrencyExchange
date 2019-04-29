package com.exchange.rates.data.source

import com.exchange.rates.data.model.Rates
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class CachingExchangeRateRepository @Inject constructor(
    @Local var local: ExchangeRateDataSource,
    @Remote var remote: ExchangeRateDataSource)
    : ExchangeRateRepository {

    override fun getRates(date: Long): Single<Rates> {
        return local
            .getRates(date)
            .switchIfEmpty(
                remote
                    .getRates(date)
                    .doAfterSuccess { rates: Rates -> local.createOrUpdateRates(rates) }
            )
            .toSingle()
    }

    override fun getLastUpdateDate(): Maybe<Rates> {
        return local.getLastRate()
    }
}