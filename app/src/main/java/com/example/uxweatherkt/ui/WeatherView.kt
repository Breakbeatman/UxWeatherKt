package com.example.uxweatherkt.ui

interface WeatherView: LoadingView {
    fun showWeather(message: String)
}