package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.Contract
import com.example.uxweatherkt.weather.WeatherModel

class WeatherPresenter (private var weatherView: Contract.WeatherView, private var weatherModel: Contract.WeatherModel = WeatherModel()) : Contract.WeatherPresenter {

    private lateinit var message: String

    override fun requestToUpdate() {
        message = weatherModel.loadCurrentWeather().getTemperature
        weatherModel.loadHourlyWeather()
        weatherModel.loadDailyWeather()
        weatherView.showWeather(message)
    }

}