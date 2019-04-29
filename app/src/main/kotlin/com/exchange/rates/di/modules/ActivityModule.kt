package com.exchange.rates.di.modules

import com.exchange.rates.presentation.converter.ConverterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.exchange.rates.presentation.detail.DetailActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ConverterFragmentProvider::class])
    abstract fun bindConverterActivity(): ConverterActivity

    @ContributesAndroidInjector(modules = [DetailFragmentProvider::class])
    abstract fun bindDetailActivity(): DetailActivity
}