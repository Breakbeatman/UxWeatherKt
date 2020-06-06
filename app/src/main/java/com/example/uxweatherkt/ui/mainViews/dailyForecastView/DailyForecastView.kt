package com.example.uxweatherkt.ui.mainViews.dailyForecastView

import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.ui.WeatherView

interface DailyForecastView: WeatherView {
    fun bindData(dailyForecastRow: List<DayForecastRow>?)

    interface Listener {
        fun onDailyAdapterItemClick(dayForecastRow: DayForecastRow)
    }
}