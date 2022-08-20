package com.ibrahimethem.todoremember.data.networkdatasource

import com.ibrahimethem.todoremember.data.network.weather.WeatherService
import com.ibrahimethem.todoremember.domain.model.weather.WeaterList
import retrofit2.Response

class WeatherNetworkDataSource(private val weatherService: WeatherService) {
    suspend fun getLocationWeather(
        lat : String,
        lon : String,
        apiKey : String,
        lang : String
    ) : Response<WeaterList> {
        return weatherService.getLocationWeather(
            lat = lat,
            lon = lon,
            apiKey = apiKey,
            lang = lang
        )
    }
}