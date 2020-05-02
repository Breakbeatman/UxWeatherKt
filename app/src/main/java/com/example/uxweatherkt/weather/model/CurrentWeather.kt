package com.example.uxweatherkt.weather.model

class CurrentWeather(
    val weather: Weather,
    val pod: String,
    val temp: Double,
    val feelLike: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double
) {
    override fun toString(): String {
        return "CurrentWeather(weather=$weather, pod='$pod', temp=$temp, feelLike=$feelLike, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed)"
    }
}