package com.example.uxweatherkt.Model

import com.example.uxweatherkt.Contract

class WeatherModel : Contract.WeatherModel{

    override fun loadWeather(): Weather {

        return Weather("1")

    }

}