package com.exchange.rates.util

import com.google.gson.annotations.SerializedName

enum class Currency(val symbol: String) {
    @SerializedName("GBP")
    GBP("GBP"),
    @SerializedName("EUR")
    EUR("EUR"),
    @SerializedName("USD")
    USD("USD")
}