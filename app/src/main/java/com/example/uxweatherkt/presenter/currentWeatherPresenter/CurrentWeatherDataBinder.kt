package com.example.uxweatherkt.presenter.currentWeatherPresenter

import com.example.uxweatherkt.*
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.presenter.util.IconWeatherBinder
import com.example.uxweatherkt.weather.model.CurrentWeather
import java.text.SimpleDateFormat
import java.util.*

class CurrentWeatherDataBinder(private val iconWeatherBinder: IconWeatherBinder) {

    fun bindCurrentWeatherView(currentWeather: CurrentWeather): CurrentWeatherRow {
        val date = formatDate(System.currentTimeMillis())
        val temp = currentWeather.temp.toInt().toString() + DEGREE_UNIT
        val feelLike = currentWeather.feelLike.toInt().toString() + DEGREE_UNIT
        val pressure = currentWeather.pressure.toInt().toString() + MMHG_UNIT
        val humidity = currentWeather.humidity.toInt().toString() + PERCENT_UNIT
        val windSpeed = currentWeather.windSpeed.toInt().toString() + MS
        val windDir = " - " + currentWeather.windDir + ", "
        val uvIndex = currentWeather.uvIndex.toString()
        val visibility  =  currentWeather.visibility.toString() + KM_UNIT
        val dewPoint  =  currentWeather.dewPoint.toInt().toString() + DEGREE_UNIT
        val weatherDescription = currentWeather.weather.description
        val iconId = iconWeatherBinder.bindIconId(currentWeather.weather.code, currentWeather.pod)
        val backgroundId = iconWeatherBinder.bindBackgroundId(currentWeather.weather.code)
        return CurrentWeatherRow(
            date,
            temp,
            feelLike,
            pressure,
            humidity,
            windSpeed,
            windDir,
            uvIndex,
            visibility,
            dewPoint,
            weatherDescription,
            iconId,
            backgroundId
        )
    }

    private fun formatDate(date: Long): String {
        val df = SimpleDateFormat("E, dMMM", Locale.getDefault())
        return df.format(date)
    }
}