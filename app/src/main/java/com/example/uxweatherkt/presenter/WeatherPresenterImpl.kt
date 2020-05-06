package com.example.uxweatherkt.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.WeatherModelListImpl
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

class WeatherPresenterImpl : WeatherPresenter {

    private var weatherView: WeatherView? = null
    private var weatherModel = WeatherModelListImpl()
    private var dataBinder = DataBinder()

    var currentWeatherData = MutableLiveData<CurrentWeatherView>()
    var hourlyForecastData = MutableLiveData<List<HourForecastView>>()
    var dailyForecastData = MutableLiveData<List<DayForecastView>>()

    override fun getData() {
        TODO("Not yet implemented")
    }

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }

    fun currentWeatherViewIsReady() {
        weatherModel.loadCurrentWeather()
    }

    fun hourlyWeatherViewIsReady() {
        weatherModel.loadHourlyForecast()
    }

    fun dailyWeatherViewIsReady() {
        weatherModel.loadDailyForecast()
    }

    fun currentWeatherReady(currentWeather: CurrentWeather) {
        val currentWeatherView = dataBinder.bindCurrentWeatherView(currentWeather)
        currentWeatherData.postValue(currentWeatherView)
    }

    fun hourlyForecastReady(hourlyForecast: ArrayList<HourForecast>) {
        val hourlyForecastView = dataBinder.bindHourlyForecastView(hourlyForecast)
        hourlyForecastData.postValue(hourlyForecastView)
    }

    fun dailyForecastReady(dailyForecast: ArrayList<DayForecast>) {
        val dailyForecastView = dataBinder.bindDailyForecastView(dailyForecast)
        dailyForecastData.postValue(dailyForecastView)
    }

    fun getCurrentLiveData(): MutableLiveData<CurrentWeatherView> {
        return currentWeatherData
    }

    fun getHourlyLiveData(): MutableLiveData<List<HourForecastView>> {
        return hourlyForecastData
    }

    fun getDailyLiveData(): MutableLiveData<List<DayForecastView>> {
        return dailyForecastData
    }
}