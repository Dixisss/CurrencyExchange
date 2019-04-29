package com.exchange.rates.data.api

import com.google.gson.annotations.SerializedName

data class RatesResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("disclaimer")
    val disclaimer: String,
    @SerializedName("license")
    val license: String,
    @SerializedName("rates")
    val rates: RateData,
    @SerializedName("timestamp")
    val timestamp: Long
)