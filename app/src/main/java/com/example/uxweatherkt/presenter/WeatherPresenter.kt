package com.example.uxweatherkt.presenter

import android.location.Location
import com.example.uxweatherkt.ui.WeatherView

interface WeatherPresenter {
    fun getData(location: Location)

    fun getData(cityName: String)

    fun attachView(weatherView: WeatherView)

    fun detachView()
}
