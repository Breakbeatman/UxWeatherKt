package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.Contract
import com.example.uxweatherkt.model.WeatherModel

class WeatherPresenter (private var weatherView: Contract.WeatherView, private var weatherModel: Contract.WeatherModel = WeatherModel()) : Contract.WeatherPresenter {

    private lateinit var message: String

    override fun requestToUpdate() {
        message = weatherModel.loadWeather().getTemperature
        weatherView.showWeather(message)
    }

}