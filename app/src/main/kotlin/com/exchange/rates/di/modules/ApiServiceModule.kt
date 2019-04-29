package com.exchange.rates.di.modules

import com.exchange.rates.Config
import com.exchange.rates.data.api.ExchangeRatesServiceApi
import dagger.Module
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Converter
import javax.inject.Named

@Module
class ApiServiceModule {

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String {
        return Config.API_HOST
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRxJavaAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeService(retrofit: Retrofit): ExchangeRatesServiceApi {
        return retrofit.create<ExchangeRatesServiceApi>(ExchangeRatesServiceApi::class.java)
    }

    companion object {
        private const val BASE_URL = "base_url"
    }
}