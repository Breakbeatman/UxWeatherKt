package com.example.uxweatherkt.presenter.dailyForecastPresenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.DayForecastRow

interface DailyForecastPresenter: WeatherPresenter {
    fun getLiveData(): MutableLiveData<List<DayForecastRow>>
}