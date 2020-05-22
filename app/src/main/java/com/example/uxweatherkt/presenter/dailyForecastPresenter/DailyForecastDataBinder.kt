package com.example.uxweatherkt.presenter.dailyForecastPresenter

import com.example.uxweatherkt.DEGREE_UNIT
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.presenter.util.IconWeatherBinder
import com.example.uxweatherkt.weather.model.DayForecast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DailyForecastDataBinder(private val iconWeatherBinder: IconWeatherBinder) {

    fun bindDailyForecastView(dailyForecast: ArrayList<DayForecast>): ArrayList<DayForecastRow> {
        val dailyForecastView = ArrayList<DayForecastRow>()
        for (i in dailyForecast.indices) {
            val date = formatDate(dailyForecast[i].date)
            val dayOfWeek = formatDayOfWeek(dailyForecast[i].date)
            val maxTemp = dailyForecast[i].maxTemp.toString() + DEGREE_UNIT
            val minTemp = dailyForecast[i].minTemp.toString() + DEGREE_UNIT
            val maxTempFeelLike = dailyForecast[i].maxTempFeelLike.toString() + DEGREE_UNIT
            val minTempFeelLike = dailyForecast[i].minTempFeelLike.toString() + DEGREE_UNIT
            val pressure: String = dailyForecast[i].pressure.toString()
            val humidity: String = dailyForecast[i].humidity.toString()
            val windSpeed: String = dailyForecast[i].windSpeed.toString()
            val iconId: Int = iconWeatherBinder.bindIconId(dailyForecast[i].weather.code, "d")
            dailyForecastView.add(
                DayForecastRow(
                    date,
                    dayOfWeek,
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

    private fun formatDate(date: Long): String {
        val df = SimpleDateFormat("dd MMM", Locale.getDefault())
        return df.format(date*1000)
    }

    private fun formatDayOfWeek(date: Long): String {
        val df = SimpleDateFormat("EEE", Locale.getDefault())
        return df.format(date * 1000)
    }
}