package com.example.uxweatherkt.ui

interface WeatherView: LoadingView {
    fun showWeather()
    fun dataIsNotAvailable()
}