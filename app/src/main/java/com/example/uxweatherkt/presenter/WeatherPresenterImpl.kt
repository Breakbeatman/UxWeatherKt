package com.example.uxweatherkt.presenter

import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

class WeatherPresenterImpl : WeatherPresenter, WeatherModelImpl.Listener {

    private var weatherView: WeatherView? = null
    private var weatherModel = WeatherModelImpl(this)
    private var dataBinder = DataBinder()

    var currentWeatherData = MutableLiveData<CurrentWeatherView>()
    var hourlyForecastData = MutableLiveData<List<HourForecastView>>()
    var dailyForecastData = MutableLiveData<List<DayForecastView>>()

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
        val currentWeatherView = dataBinder.bindCurrentWeather(currentWeather)
        currentWeatherData.postValue(currentWeatherView)
    }

    override fun hourlyForecastReady(hourlyForecast: ArrayList<HourForecast>) {
//        TODO: binder
        val hourlyForecastView = ArrayList<HourForecastView>()
        hourlyForecastData.postValue(hourlyForecastView)
    }

    override fun dailyForecastReady(dailyForecast: ArrayList<DayForecast>) {
//        TODO: binder
        val dailyForecastView = ArrayList<DayForecastView>()
        dailyForecastData.postValue(dailyForecastView)
    }

    override fun getCurrentLiveData(): MutableLiveData<CurrentWeatherView> {
        return currentWeatherData
    }

    override fun getHourlyLiveData(): MutableLiveData<List<HourForecastView>> {
        return hourlyForecastData
    }

    override fun getDailyLiveData(): MutableLiveData<List<DayForecastView>> {
        return dailyForecastData
    }
}