package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class HourlyForecastPresenterImpl(
    val weatherModel: WeatherModel,
    val hourlyForecastDataBinder: HourlyForecastDataBinder
) : HourlyForecastPresenter {

    private var weatherView: WeatherView? = null
    private var hourlyForecastRow: ArrayList<HourForecastRow>? = null

    private var liveData = MutableLiveData<List<HourForecastRow>>()

    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun getData(location: Location) {
        initCoordinates(location)
        if (hourlyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val hourlyForecast = weatherModel.loadHourlyForecastBy(latitude, longitude)
                    if (hourlyForecast == null) {
                        liveData.postValue(null)
                        return
                    }
                    hourlyForecastRow =
                        hourlyForecastDataBinder.bindHourlyForecastView(hourlyForecast)
                    liveData.postValue(hourlyForecastRow)
                }
            }.start()
        } else {
            liveData.postValue(hourlyForecastRow)
        }
    }

    override fun getData(cityName: String) {
        if (hourlyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val hourlyForecast = weatherModel.loadHourlyForecastBy(cityName)
                    if (hourlyForecast == null) {
                        liveData.postValue(null)
                        return
                    }
                    hourlyForecastRow =
                        hourlyForecastDataBinder.bindHourlyForecastView(hourlyForecast)
                    liveData.postValue(hourlyForecastRow)
                }
            }.start()
        } else {
            liveData.postValue(hourlyForecastRow)
        }
    }

    override fun getLiveData(): MutableLiveData<List<HourForecastRow>> {
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