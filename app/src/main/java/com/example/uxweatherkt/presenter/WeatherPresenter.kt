package com.example.uxweatherkt.presenter

import android.location.Location
import com.example.uxweatherkt.ui.WeatherView

interface WeatherPresenter {
    fun getWeatherData(location: Location)

    fun getWeatherData(cityName: String)

    fun attachView(weatherView: WeatherView)

    fun detachView()
}
