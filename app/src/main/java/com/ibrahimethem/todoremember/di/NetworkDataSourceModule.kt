package com.ibrahimethem.todoremember.di

import com.ibrahimethem.todoremember.data.network.quote.QuoteService
import com.ibrahimethem.todoremember.data.network.weather.WeatherService
import com.ibrahimethem.todoremember.data.networkdatasource.QuoteNetworkDataSource
import com.ibrahimethem.todoremember.data.networkdatasource.WeatherNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDataSourceModule {
    @[Provides Singleton]
    fun provideWeatherNetworkDataSource(weatherService: WeatherService) = WeatherNetworkDataSource(weatherService)

    @[Provides Singleton]
    fun provideQuoteNetworkDataSource(quoteService: QuoteService) = QuoteNetworkDataSource(quoteService)
}