package com.exchange.rates.di.modules

import com.exchange.rates.presentation.detail.DetailFragment
import com.exchange.rates.presentation.detail.DetailView
import dagger.Binds
import dagger.Module

@Module
abstract class DetailFragmentModule {

    @Binds
    abstract fun provideDetailView(detailFragment: DetailFragment): DetailView
}