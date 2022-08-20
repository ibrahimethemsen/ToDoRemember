package com.ibrahimethem.todoremember.domain.usecase


import com.ibrahimethem.todoremember.data.repository.WeatherRepository
import com.ibrahimethem.todoremember.domain.model.weather.WeaterList
import com.ibrahimethem.todoremember.util.Resource

class LocationWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        lat : String,
        lon : String,
        apiKey : String,
        lang : String
    ): Resource<WeaterList> {
        return weatherRepository.getLocation(
            lat = lat,
            lon = lon,
            apiKey = apiKey,
            lang = lang
        )
    }
}