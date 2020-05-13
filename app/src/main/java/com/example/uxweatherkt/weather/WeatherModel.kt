package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

interface WeatherModel {
    fun loadCurrentWeatherBy(latitude: String, longitude: String): CurrentWeather?

    fun loadCurrentWeatherBy(cityName: String): CurrentWeather?

    fun loadHourlyForecastBy(latitude: String, longitude: String): ArrayList<HourForecast>?

    fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>?

    fun loadDailyForecastBy(latitude: String, longitude: String): ArrayList<DayForecast>?

    fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>?
}