package com.example.uxweatherkt.weather.model

class HourForecast(val weather: Weather, val pod: String, val date: Long, val eveTemp: Double,
                   val pressure: Double, val humidity: Double, val windSpeed: Double) {
    override fun toString(): String {
        return "OneHourForecast(weather=$weather, pod='$pod', date=$date, eveTemp=$eveTemp, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed)"
    }
}