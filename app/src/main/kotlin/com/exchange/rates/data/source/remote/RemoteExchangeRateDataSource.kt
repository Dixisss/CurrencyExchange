package com.exchange.rates.data.source.remote

import com.exchange.rates.data.api.ExchangeRatesServiceApi
import com.exchange.rates.data.api.RatesResponse
import com.exchange.rates.data.model.Rates
import com.exchange.rates.data.source.ExchangeRateDataSource
import com.exchange.rates.util.Currency
import io.reactivex.Completable
import io.reactivex.Maybe
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class RemoteExchangeRateDataSource @Inject constructor(
    var api: ExchangeRatesServiceApi,
    var app_id: String
): ExchangeRateDataSource {

    override fun getRates(date: Long): Maybe<Rates> {
        return api
            .getRates(app_id)
            .toMaybe()
            .map { response: RatesResponse ->
                val base = getCurrency(response.base)
                val map = mutableMapOf<Currency, Double>().apply {
                    response.rates.let {
                        put(Currency.EUR, it.eur)
                        put(Currency.GBP, it.gbp)
                        put(Currency.USD, it.usd)
                    }
                }
                Rates(base, response.timestamp, date, map)
            }
    }

    override fun getLastRate(): Maybe<Rates> {
        throw UnsupportedOperationException()
    }

    override fun createOrUpdateRates(rates: Rates): Completable {
        throw UnsupportedOperationException()
    }

    override fun deleteRates(): Completable {
        throw UnsupportedOperationException()
    }

    private fun getCurrency(s: String): Currency =
        Currency
            .values()
            .first { currency -> s == currency.symbol }
}