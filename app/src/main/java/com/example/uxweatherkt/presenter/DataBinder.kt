package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.weather.model.CurrentWeather

class DataBinder {

    fun bindCurrentWeather(currentWeather: CurrentWeather): CurrentWeatherView {
        val temp= currentWeather.temp.toString()
        val pressure = currentWeather.pressure.toString()
        val humidity = currentWeather.humidity.toString()
        val windSpeed = currentWeather.windSpeed.toString()
        val iconId = bindIconId(currentWeather.weather.code, currentWeather.pod)
        return CurrentWeatherView(temp, pressure, humidity, windSpeed, iconId)
    }

    private fun bindIconId(code: Int, pod: String): Int {
        return 222
    }
}