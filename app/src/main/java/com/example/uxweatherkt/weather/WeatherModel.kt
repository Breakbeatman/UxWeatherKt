package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

interface WeatherModel {
    fun loadCurrentWeather(latitude: String, longitude: String): CurrentWeather?

    fun loadHourlyForecast(latitude: String, longitude: String): ArrayList<HourForecast>?

    fun loadDailyForecast(latitude: String, longitude: String): ArrayList<DayForecast>?
}