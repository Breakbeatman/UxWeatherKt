package com.example.uxweatherkt

import android.content.Context
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastDataBinder
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastDataBinder
import com.example.uxweatherkt.presenter.util.IconWeatherBinder
import com.example.uxweatherkt.presenter.util.UserLocationFinder
import com.example.uxweatherkt.ui.userLocation.UserLocation
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.WeatherModelStubImpl
import com.example.uxweatherkt.weather.repository.HttpRequestExecutor
import com.example.uxweatherkt.weather.util.WeatherJSONParser


class DependencyRoot(context: Context) {
    private val weatherJSONParser = WeatherJSONParser()
    private val remoteRequestMaker = HttpRequestExecutor()
//    val weatherModel = WeatherModelImpl(weatherJSONParser, remoteRequestMaker)
    val weatherModel = WeatherModelStubImpl()
    private val iconBinder = IconWeatherBinder()
    val currentWeatherDataBinder = CurrentWeatherDataBinder(iconBinder)
    val hourlyForecastDataBinder = HourlyForecastDataBinder(iconBinder)
    val dailyWeatherDataBinder = DailyForecastDataBinder(iconBinder)
    val userLocation = UserLocation()
    val locationFinder = UserLocationFinder(context, userLocation)
}