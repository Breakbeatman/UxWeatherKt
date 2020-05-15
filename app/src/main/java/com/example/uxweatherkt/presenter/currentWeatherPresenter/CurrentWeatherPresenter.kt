package com.example.uxweatherkt.presenter.currentWeatherPresenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow

interface CurrentWeatherPresenter: WeatherPresenter {
    fun getLiveData(): MutableLiveData<CurrentWeatherRow>
}