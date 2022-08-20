package com.ibrahimethem.todoremember.domain.model.weather.uistate

data class WeatherModel(
    val temp : Int? = -1,
    val description : String? = "Hava durumu",
    val weather : String? = "icon"
)
