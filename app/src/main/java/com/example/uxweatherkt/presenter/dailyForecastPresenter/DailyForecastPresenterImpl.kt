package com.example.uxweatherkt.presenter.dailyForecastPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.presenter.util.Coordinates
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class DailyForecastPresenterImpl(
    val weatherModel: WeatherModel,
    val dailyForecastDataBinder: DailyForecastDataBinder
) : DailyForecastPresenter {

    private var weatherView: WeatherView? = null
    private var dailyForecastRow: ArrayList<DayForecastRow>? = null

    private var liveData = MutableLiveData<List<DayForecastRow>>()

    private lateinit var coordinates: Coordinates

    override fun getWeatherData(location: Location) {
        initCoordinates(location)
        loadWeatherByCoors()
    }

    private fun loadWeatherByCoors() {
        if (dailyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val dailyForecast = weatherModel.loadDailyForecastBy(coordinates)
                    if (dailyForecast == null) {
                        liveData.postValue(null)
                        return
                    }
                    dailyForecastRow = dailyForecastDataBinder.bindDailyForecastView(dailyForecast)
                    liveData.postValue(dailyForecastRow)
                }
            }.start()
        } else {
            liveData.postValue(dailyForecastRow)
        }
    }

    override fun getWeatherData(cityName: String) {
        loadWeatherBy(cityName)
    }

    private fun loadWeatherBy(cityName: String) {
        if (dailyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val dailyForecast = weatherModel.loadDailyForecastBy(cityName)
                    if (dailyForecast == null) {
                        liveData.postValue(null)
                        return
                    }
                    dailyForecastRow = dailyForecastDataBinder.bindDailyForecastView(dailyForecast)
                    liveData.postValue(dailyForecastRow)
                }
            }.start()
        } else {
            liveData.postValue(dailyForecastRow)
        }
    }

    override fun getLiveData(): MutableLiveData<List<DayForecastRow>> {
        return liveData
    }

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }

    private fun initCoordinates(location: Location) {
        val latitude = location.latitude.toString()
        val longitude = location.longitude.toString()
        coordinates = Coordinates(latitude, longitude)
    }
}