package com.exchange.rates.data

data class Result<out T>(val status: Status, val data: T?, val t: Throwable?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(t: Throwable, data: T?): Result<T> {
            return Result(Status.FAILED, data, t)
        }
    }
}
