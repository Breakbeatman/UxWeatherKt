package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.presenter.util.Coordinates
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class HourlyForecastPresenterImpl(
    val weatherModel: WeatherModel,
    val hourlyForecastDataBinder: HourlyForecastDataBinder
) : HourlyForecastPresenter {

    private var weatherView: WeatherView? = null
    private var hourlyForecastRow: ArrayList<HourForecastRow>? = null

    private var liveData = MutableLiveData<List<HourForecastRow>>()

    private lateinit var coordinates: Coordinates

    override fun getWeatherData(location: Location) {
        initCoordinates(location)
        loadWeatherByCoors()
    }

    private fun loadWeatherByCoors() {
        if (hourlyForecastRow == null) {
            object : Thread() {
                override fun run() {
                    val hourlyForecast = weatherModel.loadHourlyForecastBy(coordinates)
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

    override fun getWeatherData(cityName: String) {
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
        val latitude = location.latitude.toString()
        val longitude = location.longitude.toString()
        coordinates = Coordinates(latitude, longitude)
    }
}