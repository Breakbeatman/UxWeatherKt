package com.example.uxweatherkt.ui.mainViews.currentWeatherView

import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.ui.WeatherView

interface CurrentWeatherView: WeatherView {
    fun bindData(currentWeatherRow: CurrentWeatherRow?)
}