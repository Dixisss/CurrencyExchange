package com.exchange.rates.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeRatesServiceApi {

    @GET("/api/latest.json?symbols=GBP,EUR,USD")
    fun getRates(@Query("app_id") key: String): Single<RatesResponse>
}