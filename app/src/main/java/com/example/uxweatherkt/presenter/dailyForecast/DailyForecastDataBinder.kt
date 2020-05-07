package com.example.uxweatherkt.presenter.dailyForecast

import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.presenter.util.IconBinder
import com.example.uxweatherkt.weather.model.DayForecast

class DailyForecastDataBinder(private val iconBinder: IconBinder) {

    fun bindDailyForecastView(dailyForecast: ArrayList<DayForecast>): ArrayList<DayForecastView> {
        val degree = "\u00B0C"
        val dailyForecastView = ArrayList<DayForecastView>()
        for (i in dailyForecast.indices) {
            val date = dailyForecast[i].date.toString()
            val maxTemp = dailyForecast[i].maxTemp.toString() + degree
            val minTemp = dailyForecast[i].minTemp.toString() + degree
            val maxTempFeelLike = dailyForecast[i].maxTempFeelLike.toString() + degree
            val minTempFeelLike = dailyForecast[i].minTempFeelLike.toString() + degree
            val pressure: String = dailyForecast[i].pressure.toString()
            val humidity: String = dailyForecast[i].humidity.toString()
            val windSpeed: String = dailyForecast[i].windSpeed.toString()
            val iconId: Int = iconBinder.bindIconId(dailyForecast[i].weather.code, "d")
            dailyForecastView.add(
                DayForecastView(
                    date,
                    maxTemp,
                    minTemp,
                    maxTempFeelLike,
                    minTempFeelLike,
                    pressure,
                    humidity,
                    windSpeed,
                    iconId
                )
            )
        }
        return dailyForecastView
    }
}