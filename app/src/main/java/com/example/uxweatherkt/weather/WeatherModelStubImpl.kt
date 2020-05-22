package com.example.uxweatherkt.weather

import android.opengl.Visibility
import com.example.uxweatherkt.presenter.util.Coordinates
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast
import com.example.uxweatherkt.weather.model.Weather
import java.util.*
import kotlin.collections.ArrayList

// Заглушка
class WeatherModelStubImpl : WeatherModel {

    private var currentTime = System.currentTimeMillis()
    private var random = Random(currentTime)

    //    TODO: random weather generator
    override fun loadCurrentWeatherBy(coordinates: Coordinates): CurrentWeather {
        return currentWeatherList()
    }

    override fun loadCurrentWeatherBy(cityName: String): CurrentWeather? {
        return currentWeatherList()
    }

    override fun loadHourlyForecastBy(coordinates: Coordinates): ArrayList<HourForecast> {
        return hourlyForecast()
    }

    override fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>? {
        return hourlyForecast()
    }

    override fun loadDailyForecastBy(coordinates: Coordinates): ArrayList<DayForecast> {
        return dailyForecast()
    }

    override fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>? {
        return dailyForecast()
    }


    private fun currentWeatherList(): CurrentWeather {
        return CurrentWeather(
            Weather(weatherCodeGen(), "Cloudy"),
            podGen(),
            tempGen(),
            tempGen(),
            pressureGen(),
            humidityGen(),
            windSpeedGen(),
            "NORTH",
            uvIndexGen(),
            visibilityGen(),
            dewPointGen()
        )
    }

    private fun hourlyForecast(): ArrayList<HourForecast> {
        val list = ArrayList<HourForecast>()
        for (i in 0..24) {
            val hourForecast = HourForecast(
                Weather(800, "Clear"),
                "d",
                dateHourlyGen(i),
                3.0,
                1.0,
                1.0,
                2.0,
                3.0
            )
            list.add(hourForecast)
        }
        return list
    }

    private fun dailyForecast(): ArrayList<DayForecast> {
        val list = ArrayList<DayForecast>()
        for (i in 0..14) {
            val dayForecast = DayForecast(
                Weather(804, "Cloudy"),
                dateDailyGen(i),
                22.0,
                12.0,
                13.0,
                13.0,
                13.0,
                11.0,
                13.0
            )
            list.add(dayForecast)
        }
        return list
    }

    private fun podGen(): String {
        return when (random.nextInt(2)) {
            0 -> "d"
            else -> "n"
        }
    }

    private fun dateHourlyGen(count: Int): Long {
        val hour = 3600
        return currentTime + hour * count
    }

    private fun dateDailyGen(count: Int): Long {
        val day = 3600 * 24
        return currentTime + day * count
    }

    private fun tempGen(): Double {
        return Math.random() * 100 - 50
    }

    private fun pressureGen(): Double {
        return Math.random() * 700 - 50
    }

    private fun humidityGen(): Double {
        return Math.random() * 100
    }

    private fun windSpeedGen(): Double {
        return Math.random() * 20
    }

    private fun uvIndexGen(): Int {
        return (Math.random() * 11).toInt()
    }

    private fun visibilityGen(): Int {
        return (Math.random() * 100).toInt()
    }

    private fun dewPointGen(): Double {
        return Math.random() * 100
    }

    private fun weatherCodeGen(): Int {
        val array = arrayOf(
            200,
            300,
            520,
            521,
            600,
            700,
            711,
            721,
            731,
            800,
            801,
            802,
            803,
            804
        )
        println(array.size)
        return array[(Math.random()*14).toInt()]
    }
}