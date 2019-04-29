package com.exchange.rates

import com.exchange.rates.di.DaggerAppComponent
import dagger.android.support.DaggerApplication
import timber.log.Timber

class ExchangeApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector() =
        DaggerAppComponent
            .factory()
            .create(applicationContext)

    companion object {
        lateinit var instance: ExchangeApplication private set
    }
}