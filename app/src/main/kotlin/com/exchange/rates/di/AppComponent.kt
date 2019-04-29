package com.exchange.rates.di

import dagger.Component
import javax.inject.Singleton
import android.content.Context
import com.exchange.rates.ExchangeApplication
import com.exchange.rates.di.annotations.AppContext
import com.exchange.rates.di.modules.*
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        OkHttpClientModule::class,
        ApiServiceModule::class,
        ExchangeRepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<ExchangeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@AppContext @BindsInstance applicationContext: Context): AppComponent
    }
}