package com.example.uxweatherkt.weather

import com.example.uxweatherkt.presenter.util.Coordinates
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

interface WeatherModel {
    fun loadCurrentWeatherBy(coordinates: Coordinates): CurrentWeather?

    fun loadCurrentWeatherBy(cityName: String): CurrentWeather?

    fun loadHourlyForecastBy(coordinates: Coordinates): ArrayList<HourForecast>?

    fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>?

    fun loadDailyForecastBy(coordinates: Coordinates): ArrayList<DayForecast>?

    fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>?
}