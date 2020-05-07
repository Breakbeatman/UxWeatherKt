package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.HourForecastView

interface HourlyForecastPresenter: WeatherPresenter {
    fun getLiveData(): MutableLiveData<List<HourForecastView>>
}