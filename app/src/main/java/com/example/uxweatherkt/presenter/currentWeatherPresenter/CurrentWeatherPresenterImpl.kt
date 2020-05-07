package com.example.uxweatherkt.presenter.currentWeatherPresenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class CurrentWeatherPresenterImpl(
    val weatherModel: WeatherModel,
    val currentWeatherDataBinder: CurrentWeatherDataBinder
) : CurrentWeatherPresenter {

    private var weatherView: WeatherView? = null

    private var currentWeatherData = MutableLiveData<CurrentWeatherView>()

    override fun getData() {
        object : Thread() {
            override fun run() {
                val currentWeather = weatherModel.loadCurrentWeather()
                val currentWeatherView =
                    currentWeatherDataBinder.bindCurrentWeatherView(currentWeather)
                currentWeatherData.postValue(currentWeatherView)
            }
        }.start()
    }

    override fun getLiveData(): MutableLiveData<CurrentWeatherView> {
        return currentWeatherData
    }

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }

}