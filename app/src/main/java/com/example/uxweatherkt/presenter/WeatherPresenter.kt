package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.ui.WeatherView

interface WeatherPresenter {
    fun currentWeatherViewIsReady()

    fun hourlyWeatherViewIsReady()

    fun dailyWeatherViewIsReady()

    fun attachView(weatherView: WeatherView)

    fun detachView()
}
