package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.weather.WeatherModel

class HourlyForecastPresenterImpl(
    weatherModel: WeatherModel,
    hourlyForecastDataBinder: HourlyForecastDataBinder
): HourlyForecastPresenter {

    private var weatherView: WeatherView? = null

    private var liveData = MutableLiveData<List<HourForecastView>>()

    override fun getData(location: Location) {
        TODO("Not yet implemented")
    }

    override fun getLiveData(): MutableLiveData<List<HourForecastView>> {
        return liveData
    }

    override fun attachView(weatherView: WeatherView) {
        this.weatherView = weatherView
    }

    override fun detachView() {
        weatherView = null
    }
}