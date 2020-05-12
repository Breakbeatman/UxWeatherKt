package com.example.uxweatherkt.presenter.dailyForecast

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListFragment
import com.example.uxweatherkt.weather.WeatherModel

class DailyForecastPresenterImpl(
    val weatherModel: WeatherModel,
    val dailyForecastDataBinder: DailyForecastDataBinder
) : DailyForecastPresenter, DailyForecastListFragment.Listener {

    private var weatherView: WeatherView? = null

    private var dailyForecastData = MutableLiveData<List<DayForecastView>>()

    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun getData(location: Location) {
        initCoordinates(location)
        object : Thread() {
            override fun run() {
                val dailyForecast = weatherModel.loadDailyForecast(latitude, longitude)
                if (dailyForecast == null) {
                    dailyForecastData.postValue(null)
                    return
                }
                val dailyForecastView = dailyForecastDataBinder.bindDailyForecastView(dailyForecast)
                dailyForecastData.postValue(dailyForecastView)
            }
        }.start()
    }

    override fun getLiveData(): MutableLiveData<List<DayForecastView>> {
        return dailyForecastData
    }

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }

    override fun onDailyForecastListItemClicked() {
        TODO("Not yet implemented")
    }

    private fun initCoordinates(location: Location) {
        latitude = location.latitude.toString()
        longitude = location.longitude.toString()
    }
}