package com.example.uxweatherkt.presenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.ui.WeatherView

interface WeatherPresenter {
    fun currentWeatherViewIsReady()

    fun hourlyWeatherViewIsReady()

    fun dailyWeatherViewIsReady()

    fun attachView(weatherView: WeatherView)

    fun detachView()

    fun getCurrentLiveData(): MutableLiveData<CurrentWeatherView>

    fun getHourlyLiveData(): MutableLiveData<List<HourForecastView>>

    fun getDailyLiveData(): MutableLiveData<List<DayForecastView>>
}
