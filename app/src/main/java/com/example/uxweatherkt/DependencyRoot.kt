package com.example.uxweatherkt

import android.content.Context
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastDataBinder
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastDataBinder
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.presenter.util.LocationFinder
import com.example.uxweatherkt.ui.userLocation.UserLocation
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.WeatherModelListImpl
import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser


class DependencyRoot(context: Context) {
    private val weatherJSONParser = WeatherJSONParser()
    private val remoteRequestMaker = RemoteRequestMaker()
//    val weatherModel = WeatherModelImpl(weatherJSONParser, remoteRequestMaker)
    val weatherModel = WeatherModelListImpl()
    private val iconBinder = IconBinder()
    val currentWeatherDataBinder = CurrentWeatherDataBinder(iconBinder)
    val hourlyForecastDataBinder = HourlyForecastDataBinder(iconBinder)
    val dailyWeatherDataBinder = DailyForecastDataBinder(iconBinder)
    val userLocation = UserLocation()
    val locationFinder = LocationFinder(context, userLocation)
}