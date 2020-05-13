package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast
import com.example.uxweatherkt.weather.model.Weather

// Заглушка
class WeatherModelListImpl : WeatherModel {

    override fun loadCurrentWeatherBy(latitude: String, longitude: String): CurrentWeather {
        return currentWeatherList()
    }

    override fun loadCurrentWeatherBy(cityName: String): CurrentWeather? {
        return currentWeatherList()
    }

    override fun loadHourlyForecastBy(latitude: String, longitude: String): ArrayList<HourForecast> {
        return hourlyForecast()
    }

    override fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>? {
        return hourlyForecast()
    }

    override fun loadDailyForecastBy(latitude: String, longitude: String): ArrayList<DayForecast> {
        return dailyForecast()
    }

    override fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>? {
        return dailyForecast()
    }


    private fun currentWeatherList(): CurrentWeather {
        return CurrentWeather(
            Weather(1000, "what"),
            "d",
            123.0,
            123.0,
            123.0,
            123.0,
            123.0
        )
    }

    private fun hourlyForecast(): ArrayList<HourForecast> {
        val list = ArrayList<HourForecast>()
        for (i in 0..10) {
            val hourForecast = HourForecast(
                Weather(800, "hello"),
                "d",
                123123123123,
                123.0,
                123.0,
                123.0,
                123.0,
                123.0
            )
            list.add(hourForecast)
        }
        return list
    }

    private fun dailyForecast(): ArrayList<DayForecast> {
        val list = ArrayList<DayForecast>()
        for (i in 0..10) {
            val dayForecast = DayForecast(
                Weather(800, "what"),
            121212121221,
                123.0,
                123.0,
                123.0,
                123.0,
                123.0,
                123.0,
                123.0
            )
            list.add(dayForecast)
        }
        return list
    }
}