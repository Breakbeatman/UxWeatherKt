package com.example.uxweatherkt.presenter.dailyForecastPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class DailyForecastPresenterImpl(
    val weatherModel: WeatherModel,
    val dailyForecastDataBinder: DailyForecastDataBinder
) : DailyForecastPresenter {

    private var weatherView: WeatherView? = null
    private var dailyForecastRow: ArrayList<DayForecastRow>? = null

    private var liveData = MutableLiveData<List<DayForecastRow>>()

    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun getData(location: Location) {
        initCoordinates(location)
        if (dailyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val dailyForecast = weatherModel.loadDailyForecastBy(latitude, longitude)
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

    override fun getData(cityName: String) {
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
        latitude = location.latitude.toString()
        longitude = location.longitude.toString()
    }
}