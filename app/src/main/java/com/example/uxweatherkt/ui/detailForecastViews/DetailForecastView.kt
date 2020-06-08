package com.example.uxweatherkt.ui.detailForecastViews

import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.ui.WeatherView

interface DetailForecastView: WeatherView {
    fun bindData(dailyForecastRow: List<DayForecastRow>?)
}