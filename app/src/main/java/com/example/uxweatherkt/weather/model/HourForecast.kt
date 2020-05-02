package com.example.uxweatherkt.weather.model

class HourForecast(
    val weather: Weather,
    val pod: String,
    val date: Long,
    val temp: Double,
    val feelLike: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double
) {
    override fun toString(): String {
        return "OneHourForecast(weather=$weather, pod='$pod', date=$date, eveTemp=$feelLike, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed)"
    }
}