package com.example.uxweatherkt.presenter.dailyForecast

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.DayForecastView

interface DailyForecastPresenter: WeatherPresenter {
    fun getLiveData(): MutableLiveData<List<DayForecastView>>
}