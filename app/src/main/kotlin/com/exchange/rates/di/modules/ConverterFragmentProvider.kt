package com.exchange.rates.di.modules

import com.exchange.rates.presentation.converter.ConverterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConverterFragmentProvider {

    @ContributesAndroidInjector(
        modules = [
            ConverterFragmentModule::class,
            CompositeDisposableModule::class
        ]
    )
    internal abstract fun provideConverterFragmentFactory(): ConverterFragment
}