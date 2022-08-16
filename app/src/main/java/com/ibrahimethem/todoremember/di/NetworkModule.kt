package com.ibrahimethem.todoremember.di


import com.ibrahimethem.todoremember.network.quote.QuoteService
import com.ibrahimethem.todoremember.network.weather.WeatherService
import com.ibrahimethem.todoremember.util.Consts.QUOTE_BASE_URL
import com.ibrahimethem.todoremember.util.Consts.WEATHER_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class WeatherRetrofitInstance

@Qualifier
annotation class QuoteRetrofitInstance


@Module //bir modul oldugunu soyler
@InstallIn(SingletonComponent::class) //mevcut olmasını istedigimiz hilt container
object NetworkModule {

    @Provides //Moshi instance gerektiğinde oluşturulmasını sağlıyor.
    @Singleton //her zaman aynı instance alınmasını sağlayacak
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @WeatherRetrofitInstance
    @Provides
    @Singleton
    fun weatherRetrofitInstance(
        moshi: Moshi
    ) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @QuoteRetrofitInstance
    @Provides
    @Singleton
    fun quoteRetrofitInstance(
        moshi : Moshi
    ) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(QUOTE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(@WeatherRetrofitInstance retrofit: Retrofit) : WeatherService{
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteService(@QuoteRetrofitInstance retrofit : Retrofit) : QuoteService{
        return retrofit.create(QuoteService::class.java)
    }

}