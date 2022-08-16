package com.ibrahimethem.todoremember.model.weather

import com.squareup.moshi.Json

data class WeaterList(
    @Json(name = "base")
    val base: String?,
    @Json(name = "clouds")
    val clouds: Clouds?,
    @Json(name = "cod")
    val cod: Int?,
    @Json(name = "coord")
    val coord: Coord?,
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "main")
    val main: Main?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "sys")
    val sys: Sys?,
    @Json(name = "timezone")
    val timezone: Int?,
    @Json(name = "visibility")
    val visibility: Int?,
    @Json(name = "weather")
    val weather: List<Weather>?,
    @Json(name = "wind")
    val wind: Wind?
)