package com.ibrahimethem.todoremember.data.repository

import com.ibrahimethem.todoremember.base.BaseRepo
import com.ibrahimethem.todoremember.data.networkdatasource.WeatherNetworkDataSource
import com.ibrahimethem.todoremember.domain.model.weather.WeaterList
import com.ibrahimethem.todoremember.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherNetworkDataSource: WeatherNetworkDataSource
    ) : BaseRepo() {
    suspend fun getLocation(
        lat: String,
        lon: String,
        apiKey: String,
        lang: String
    ): Resource<WeaterList> {
        return safeApiCall {
            weatherNetworkDataSource.getLocationWeather(lat = lat, lon = lon, apiKey = apiKey, lang = lang)
        }
    }
}