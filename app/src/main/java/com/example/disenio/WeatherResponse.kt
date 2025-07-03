package com.example.disenio

import com.squareup.moshi.Json

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class Current(
    @Json(name = "temp_c") val tempC: Double,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "wind_kph") val windKph: Double,
    val condition: Condition
)

data class Condition(
    @Json(name = "text") val text: String,
    @Json(name = "icon") val icon: String
)
