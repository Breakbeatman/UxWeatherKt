package com.example.uxweatherkt

import com.example.uxweatherkt.model.Weather

interface Contract {

    interface WeatherModel {
        fun loadWeather(): Weather
    }

    interface WeatherPresenter {
        fun requestToUpdate ()
    }

    interface WeatherView {
        fun showWeather(message: String)
    }



}