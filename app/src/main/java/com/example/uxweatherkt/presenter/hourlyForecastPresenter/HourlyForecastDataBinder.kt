package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.weather.model.HourForecast

class HourlyForecastDataBinder(private val iconBinder: IconBinder) {

    fun bindHourlyForecastView(hourlyForecast: ArrayList<HourForecast>): ArrayList<HourForecastView> {
        val degree = "\u00B0C"
        val hourlyForecastView = ArrayList<HourForecastView>()
        for (i in hourlyForecast.indices) {
            val date = hourlyForecast[i].date.toString()
            val temp = hourlyForecast[i].temp.toString() + degree
            val feelLike = hourlyForecast[i].feelLike.toString() + degree
            val pressure: String = hourlyForecast[i].pressure.toString()
            val humidity: String = hourlyForecast[i].humidity.toString()
            val windSpeed: String = hourlyForecast[i].windSpeed.toString()
            val iconId: Int =
                iconBinder.bindIconId(hourlyForecast[i].weather.code, hourlyForecast[i].pod)
            hourlyForecastView.add(
                HourForecastView(
                    date,
                    temp,
                    feelLike,
                    pressure,
                    humidity,
                    windSpeed,
                    iconId
                )
            )
        }
        return hourlyForecastView
    }

}