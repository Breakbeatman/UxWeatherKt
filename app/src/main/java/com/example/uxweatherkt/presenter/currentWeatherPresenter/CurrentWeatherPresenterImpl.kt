package com.example.uxweatherkt.presenter.currentWeatherPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class CurrentWeatherPresenterImpl(
    val weatherModel: WeatherModel,
    val currentWeatherDataBinder: CurrentWeatherDataBinder
) : CurrentWeatherPresenter {

    private var weatherView: WeatherView? = null
    private var currentWeatherRow: CurrentWeatherRow? = null

    private var currentWeatherData = MutableLiveData<CurrentWeatherRow>()

    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun getData(location: Location) {
        initCoordinates(location)
        if (currentWeatherRow == null) {
            object : Thread() {
                override fun run() {
                    val currentWeather = weatherModel.loadCurrentWeatherBy(latitude, longitude)
                    if (currentWeather == null) {
                        currentWeatherData.postValue(null)
                        return
                    }
                    currentWeatherRow =
                        currentWeatherDataBinder.bindCurrentWeatherView(currentWeather)
                    currentWeatherData.postValue(currentWeatherRow)
                }
            }.start()
        } else {
            currentWeatherData.postValue(currentWeatherRow)
        }
    }

    override fun getData(cityName: String) {
        if (currentWeatherRow == null) {
            object : Thread() {
                override fun run() {
                    val currentWeather = weatherModel.loadCurrentWeatherBy(cityName)
                    if (currentWeather == null) {
                        currentWeatherData.postValue(null)
                        return
                    }
                    currentWeatherRow =
                        currentWeatherDataBinder.bindCurrentWeatherView(currentWeather)
                    currentWeatherData.postValue(currentWeatherRow)
                }
            }.start()
        } else {
            currentWeatherData.postValue(currentWeatherRow)
        }
    }

    override fun getLiveData(): MutableLiveData<CurrentWeatherRow> {
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