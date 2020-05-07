package com.example.uxweatherkt

import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenterImpl
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastDataBinder
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenterImpl
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.weather.WeatherModelImpl
import com.example.uxweatherkt.weather.WeatherModelListImpl


class DependencyRoot {
    val weatherModel = WeatherModelListImpl()
    val iconBinder = IconBinder()
    val currentWeatherDataBinder = CurrentWeatherDataBinder(iconBinder)
    val dailyWeatherDataBinder = DailyForecastDataBinder(iconBinder)
//    val currentWeatherPresenter =
//        CurrentWeatherPresenterImpl(weatherModel, currentWeatherDataBinder)
//    val dailyForecastPresenter =
//        DailyForecastPresenterImpl(weatherModel, dailyWeatherDataBinder)
}