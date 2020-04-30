package com.example.uxweatherkt.weather.model

class DayForecast(
    val weather: Weather,
    val date: Long,
    val eveTemp: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double
) {
    override fun toString(): String {
        return "OneDayForecast(weather=$weather, date=$date, eveTemp=$eveTemp, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed)"
    }
}