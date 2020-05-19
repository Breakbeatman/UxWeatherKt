package com.example.uxweatherkt.presenter.hourlyForecastPresenter

import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.presenter.util.IconWeatherBinder
import com.example.uxweatherkt.weather.model.HourForecast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HourlyForecastDataBinder(private val iconWeatherBinder: IconWeatherBinder) {

    fun bindHourlyForecastView(hourlyForecast: ArrayList<HourForecast>): ArrayList<HourForecastRow> {
        val degree = "\u00B0C"
        val percent = "%"
        val hourlyForecastView = ArrayList<HourForecastRow>()
        for (i in hourlyForecast.indices) {
            val date = formatDate(hourlyForecast[i].date)
            val temp = hourlyForecast[i].temp.toInt().toString() + degree
            val feelLike = hourlyForecast[i].feelLike.toInt().toString() + degree
            val pressure: String = hourlyForecast[i].pressure.toString()
            val humidity: String = hourlyForecast[i].humidity.toInt().toString() + percent
            val windSpeed: String = hourlyForecast[i].windSpeed.toString()
            val iconId: Int =
                iconWeatherBinder.bindIconId(hourlyForecast[i].weather.code, hourlyForecast[i].pod)
            hourlyForecastView.add(
                HourForecastRow(
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

    private fun formatDate(date: Long): String {
        val df = SimpleDateFormat("hh aa", Locale.getDefault())
        return df.format(date*1000)
    }
}