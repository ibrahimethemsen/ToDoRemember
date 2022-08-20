package com.ibrahimethem.todoremember.data.network.weather

import com.ibrahimethem.todoremember.domain.model.weather.WeaterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getLocationWeather(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("appid") apiKey : String,
        @Query("lang") lang : String
    ) : Response<WeaterList>
}