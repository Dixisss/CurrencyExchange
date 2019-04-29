package com.exchange.rates.data.model

import com.exchange.rates.util.Currency

data class Rates(
    val base: Currency,
    val timestamp: Long,
    val last_update: Long,
    val rates: Map<Currency, Double>
)