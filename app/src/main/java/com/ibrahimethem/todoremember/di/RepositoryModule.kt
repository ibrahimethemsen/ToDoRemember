package com.ibrahimethem.todoremember.di

import com.ibrahimethem.todoremember.data.localdatasource.TodoLocalDataSource
import com.ibrahimethem.todoremember.data.networkdatasource.WeatherNetworkDataSource
import com.ibrahimethem.todoremember.data.repository.TodoRepository
import com.ibrahimethem.todoremember.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Provides Singleton]
    fun provideTodoRepository(localSource : TodoLocalDataSource) : TodoRepository = TodoRepository(localSource)

    @[Provides Singleton]
    fun provideWeatherRepository(weatherNetworkDataSource: WeatherNetworkDataSource) : WeatherRepository = WeatherRepository(weatherNetworkDataSource)
}