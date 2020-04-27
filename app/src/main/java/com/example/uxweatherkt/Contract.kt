package com.example.uxweatherkt

import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DailyWeather
import com.example.uxweatherkt.weather.model.HourlyWeather

interface Contract {

    interface WeatherModel {
        fun loadCurrentWeather(): CurrentWeather
        fun loadHourlyWeather(): HourlyWeather
        fun loadDailyWeather(): DailyWeather
    }

    interface WeatherPresenter {
        fun requestToUpdate ()
    }

    interface WeatherView {
        fun showWeather(message: String)
    }



}