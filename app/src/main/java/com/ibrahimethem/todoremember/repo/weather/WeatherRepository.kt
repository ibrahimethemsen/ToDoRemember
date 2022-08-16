package com.ibrahimethem.todoremember.repo.weather

import com.ibrahimethem.todoremember.base.BaseRepo
import com.ibrahimethem.todoremember.model.weather.WeaterList
import com.ibrahimethem.todoremember.network.weather.WeatherService
import com.ibrahimethem.todoremember.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService : WeatherService) : BaseRepo() {
    suspend fun getLocation(
        lat : String,
        lon : String,
        apiKey : String,
        lang : String
    ) : Resource<WeaterList> {
        return safeApiCall {
            weatherService.getLocationWeather(lat = lat, lon = lon, apiKey = apiKey, lang = lang)
        }
    }
}