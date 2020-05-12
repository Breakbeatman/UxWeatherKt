package com.example.uxweatherkt

import android.content.Context
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastDataBinder
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.presenter.util.LocationFinder
import com.example.uxweatherkt.ui.userLocation.UserLocation
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.WeatherModelListImpl
import com.example.uxweatherkt.weather.util.WeatherJSONParser


class DependencyRoot(context: Context) {
    var weatherJSONParser = WeatherJSONParser()
    val weatherModel = WeatherModelListImpl()
    private val iconBinder = IconBinder()
    val currentWeatherDataBinder = CurrentWeatherDataBinder(iconBinder)
    val dailyWeatherDataBinder = DailyForecastDataBinder(iconBinder)
    val userLocation = UserLocation()
    val locationFinder = LocationFinder(context, userLocation)

}