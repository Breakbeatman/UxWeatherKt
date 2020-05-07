package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.ui.WeatherView

interface WeatherPresenter {
    fun getData()

    fun attachView(weatherView: WeatherView)

    fun detachView()
}
