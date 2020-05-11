package com.example.uxweatherkt.presenter.currentWeatherPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.App
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class CurrentWeatherPresenterImpl(
    val weatherModel: WeatherModel,
    val currentWeatherDataBinder: CurrentWeatherDataBinder
) : CurrentWeatherPresenter {

    private var weatherView: WeatherView? = null
    private var currentWeatherView: CurrentWeatherView? = null

    private var currentWeatherData = MutableLiveData<CurrentWeatherView>()

    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun getData(location: Location) {
        initCoordinates(location)
        if (currentWeatherView == null) {
            object : Thread() {
                override fun run() {
                    val currentWeather = weatherModel.loadCurrentWeather(latitude, longitude)
                    currentWeatherView =
                        currentWeatherDataBinder.bindCurrentWeatherView(currentWeather)
                    currentWeatherData.postValue(currentWeatherView)
                }
            }.start()
        } else {
            currentWeatherData.postValue(currentWeatherView)
        }
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

    private fun initCoordinates(location: Location) {
        latitude = location.latitude.toString()
        longitude = location.longitude.toString()
    }
}