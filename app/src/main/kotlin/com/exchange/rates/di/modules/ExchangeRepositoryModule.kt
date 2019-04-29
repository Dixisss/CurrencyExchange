package com.exchange.rates.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.exchange.rates.Config
import com.exchange.rates.data.api.ExchangeRatesServiceApi
import com.exchange.rates.data.source.*
import com.exchange.rates.data.source.local.LocalExchangeRateDataSource
import com.exchange.rates.data.source.remote.RemoteExchangeRateDataSource
import com.exchange.rates.di.annotations.AppContext
import com.exchange.rates.di.annotations.AppId
import com.exchange.rates.util.AppSchedulers
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ExchangeRepositoryModule {

    @Provides
    @AppId
    fun provideAppId(): String {
        return Config.APP_ID
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@AppContext context: Context): SharedPreferences {
        return context.getSharedPreferences("com.exchange.rates_data", MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAppSchedulers(): AppSchedulers {
        return AppSchedulers(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Singleton
    @Provides
    @Remote
    fun provideRemoteDataSource(serviceApi: ExchangeRatesServiceApi, @AppId appId: String): ExchangeRateDataSource {
        return RemoteExchangeRateDataSource(serviceApi, appId)
    }

    @Singleton
    @Provides
    @Local
    fun provideLocalDataSource(sharedPreferences: SharedPreferences): ExchangeRateDataSource {
        return LocalExchangeRateDataSource(sharedPreferences, Gson())
    }

    @Singleton
    @Provides
    fun provideExchangeRateRepository(@Local localDataSource: ExchangeRateDataSource,
                        @Remote remoteDataSource: ExchangeRateDataSource): ExchangeRateRepository {
        return CachingExchangeRateRepository(localDataSource, remoteDataSource)
    }
}