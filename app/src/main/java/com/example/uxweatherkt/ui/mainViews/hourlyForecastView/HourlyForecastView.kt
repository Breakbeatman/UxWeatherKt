package com.example.uxweatherkt.ui.mainViews.hourlyForecastView

import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.ui.WeatherView

interface HourlyForecastView: WeatherView {
    fun bindData(hourlyForecastRow: List<HourForecastRow>?)

    interface Listener {
        fun onHourlyAdapterItemClick(hourForecastRow: HourForecastRow)
    }
}