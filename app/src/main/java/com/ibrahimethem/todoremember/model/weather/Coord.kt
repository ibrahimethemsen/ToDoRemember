package com.ibrahimethem.todoremember.model.weather

import com.squareup.moshi.Json

//koordinatlar
data class Coord(
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?
)