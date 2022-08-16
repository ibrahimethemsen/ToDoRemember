package com.ibrahimethem.todoremember.model.weather

import com.squareup.moshi.Json

data class Clouds(
    @Json(name="all")
    val all: Int?
)