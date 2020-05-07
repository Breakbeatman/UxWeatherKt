package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

interface WeatherModel {
    fun loadCurrentWeather(): CurrentWeather

    fun loadHourlyForecast(): ArrayList<HourForecast>

    fun loadDailyForecast(): ArrayList<DayForecast>
}