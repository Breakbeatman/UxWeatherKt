package com.example.uxweatherkt.weather.model

class DayForecast(
    val weather: Weather,
    val date: Long,
    val maxTemp: Double,
    val minTemp: Double,
    val maxTempFeelLike: Double,
    val minTempFeelLike: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double
) {
    override fun toString(): String {
        return "DayForecast(weather=$weather, date=$date, maxTemp=$maxTemp, minTemp=$minTemp, maxTempFeelLike=$maxTempFeelLike, minTempFeelLike=$minTempFeelLike, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed)"
    }
}