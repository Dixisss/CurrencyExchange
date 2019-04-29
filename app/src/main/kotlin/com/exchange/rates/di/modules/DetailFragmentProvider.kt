package com.exchange.rates.di.modules

import com.exchange.rates.presentation.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentProvider {

    @ContributesAndroidInjector(
        modules = [
            DetailFragmentModule::class,
            CompositeDisposableModule::class
        ]
    )
    abstract fun provideDetailFragmentFactory(): DetailFragment
}