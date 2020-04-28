package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.OneDayForecast
import com.example.uxweatherkt.weather.model.OneHourForecast

class WeatherPresenterImpl : WeatherPresenter, WeatherModelImpl.Listener {

    private var weatherView: WeatherView? = null
    private var weatherModel = WeatherModelImpl(this)

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }

    override fun currentWeatherViewIsReady() {
        weatherModel.loadCurrentWeather()
    }

    override fun hourlyWeatherViewIsReady() {
        weatherModel.loadHourlyForecast()
    }

    override fun dailyWeatherViewIsReady() {
        weatherModel.loadDailyForecast()
    }

    override fun currentWeatherReady(currentWeather: CurrentWeather) {
        println(currentWeather)
    }

    override fun hourlyForecastReady(hourlyForecasts: ArrayList<OneHourForecast>) {
        println(hourlyForecasts)
    }

    override fun dailyForecastReady(dailyForecast: ArrayList<OneDayForecast>) {
        println(dailyForecast)
    }


}