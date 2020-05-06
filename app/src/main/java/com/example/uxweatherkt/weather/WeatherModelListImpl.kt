package com.example.uxweatherkt.weather

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast
import com.example.uxweatherkt.weather.model.Weather

// Заглушка
class WeatherModelListImpl : WeatherModel {

    override fun loadCurrentWeather(): CurrentWeather {
        return currentWeatherList()
    }

    override fun loadHourlyForecast(): ArrayList<HourForecast> {
        return hourlyForecast()
    }

    override fun loadDailyForecast(): ArrayList<DayForecast> {
        return dailyForecast()
    }


    private fun currentWeatherList(): CurrentWeather {
        return CurrentWeather(
            Weather(800, "what"),
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
//        TODO: добавить заглушку
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