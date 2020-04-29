package com.example.uxweatherkt.weather

import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser
import org.json.JSONObject

class WeatherModelImpl(private val listener: Listener) : WeatherModel, RemoteRequestMaker.Listener {

    interface Listener {
        //        Плохо что в интерфейсе 3 метода???
        fun currentWeatherReady(currentWeather: CurrentWeather)
        fun hourlyForecastReady(hourlyForecast: ArrayList<HourForecast>)
        fun dailyForecastReady(dailyForecast: ArrayList<DayForecast>)
    }

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecast: ArrayList<HourForecast>
    private lateinit var dailyForecast: ArrayList<DayForecast>

    private var weatherJSONParser = WeatherJSONParser()
    private var remoteRequestMaker = RemoteRequestMaker(this)

    override fun loadCurrentWeather() {
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT)
    }

    override fun loadHourlyForecast() {
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_HOURLY)
    }

    override fun loadDailyForecast() {
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY)
    }

    override fun onRequestReady(requestTypeValue: String, response: JSONObject) {
        when (requestTypeValue) {
            REQUEST_TYPE_VALUE_CURRENT -> {
                currentWeather =
                    weatherJSONParser.parseCurrentWeather(response)
                listener.currentWeatherReady(currentWeather)
            }
            REQUEST_TYPE_VALUE_HOURLY -> {
                hourlyForecast =
                    weatherJSONParser.parseHourlyWeather(response)
                listener.hourlyForecastReady(hourlyForecast)
            }
            REQUEST_TYPE_VALUE_DAILY -> {
                dailyForecast =
                    weatherJSONParser.parseDailyWeather(response)
                listener.dailyForecastReady(dailyForecast)
            }
        }
    }
}