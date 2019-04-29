package com.exchange.rates.di.modules

import android.content.Context
import com.exchange.rates.di.annotations.AppContext
import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

@Module
class OkHttpClientModule {

    @Provides
    @Singleton
    fun okHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun cache(cacheFile: File) = Cache(cacheFile, 10 * 1000 * 1000) //10 MB

    @Provides
    @Singleton
    fun file(@AppContext context: Context) =
        File(context.cacheDir, "HttpCache").apply {
            mkdirs()
        }

    @Provides
    @Singleton
    fun httpLoggingInterceptor() =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
}