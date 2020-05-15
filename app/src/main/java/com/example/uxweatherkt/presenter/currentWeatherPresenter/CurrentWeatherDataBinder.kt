package com.example.uxweatherkt.presenter.currentWeatherPresenter

import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.weather.model.CurrentWeather

class CurrentWeatherDataBinder(private val iconBinder: IconBinder) {

    fun bindCurrentWeatherView(currentWeather: CurrentWeather): CurrentWeatherRow {
        val degree = "\u00B0C"
        val temp = currentWeather.temp.toString() + degree
        val feelLike = currentWeather.feelLike.toString() + degree
        val pressure = currentWeather.pressure.toString()
        val humidity = currentWeather.humidity.toString()
        val windSpeed = currentWeather.windSpeed.toString()
        val weatherDescription = currentWeather.weather.description
        val iconId = iconBinder.bindIconId(currentWeather.weather.code, currentWeather.pod)
        return CurrentWeatherRow(
            temp,
            feelLike,
            pressure,
            humidity,
            windSpeed,
            weatherDescription,
            iconId
        )
    }
}