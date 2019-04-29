package com.exchange.rates.data.source.local

import android.content.SharedPreferences
import com.exchange.rates.data.model.Rates
import com.exchange.rates.data.source.ExchangeRateDataSource
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Maybe
import java.lang.Exception
import javax.inject.Inject

class LocalExchangeRateDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson): ExchangeRateDataSource {

    override fun getRates(date: Long): Maybe<Rates> {
        return getLastRate()
            .filter { t -> date == t.last_update }
    }

    override fun getLastRate(): Maybe<Rates> {
        return Maybe.create<Rates> { emitter ->
            try {
                val rates = sharedPreferences
                    .getString(KEY_RATES, null)?.let {
                        gson.fromJson(it, Rates::class.java)
                    }
                if (!emitter.isDisposed) {
                    rates?.run {
                        emitter.onSuccess(rates)
                    }
                    emitter.onComplete()
                }
            } catch (e: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

    override fun createOrUpdateRates(rates: Rates): Completable {
        return Completable.create { emitter ->
            try {
                gson.toJson(rates).run {
                    sharedPreferences
                        .edit()
                        .putString(KEY_RATES, this)
                        .apply()
                }
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            } catch (e: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

    override fun deleteRates(): Completable {
        return Completable.create { emitter ->
            try {
                sharedPreferences
                    .edit()
                    .remove(KEY_RATES)
                    .apply()
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            } catch (e: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

    companion object {
        private const val KEY_RATES = "key_rates"
    }
}