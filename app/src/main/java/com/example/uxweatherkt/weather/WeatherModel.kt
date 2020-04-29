package com.example.uxweatherkt.weather

interface WeatherModel {
    fun loadCurrentWeather()

    fun loadHourlyForecast()

    fun loadDailyForecast()
}