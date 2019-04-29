package com.exchange.rates.data.api

import com.google.gson.annotations.SerializedName

data class RateData(
    @SerializedName("GBP")
    val gbp: Double,
    @SerializedName("EUR")
    val eur: Double,
    @SerializedName("USD")
    val usd: Double
)