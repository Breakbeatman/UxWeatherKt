package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.OneDayForecast
import com.example.uxweatherkt.weather.model.OneHourForecast

interface WeatherModel {
    fun loadCurrentWeather()

    fun loadHourlyForecast()

    fun loadDailyForecast()
}