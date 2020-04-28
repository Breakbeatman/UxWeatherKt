package com.example.uxweatherkt.weather

import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.OneDayForecast
import com.example.uxweatherkt.weather.model.OneHourForecast

import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser
import org.json.JSONObject

class WeatherModelImpl(private val listener: Listener) : WeatherModel, RemoteRequestMaker.Listener {

    interface Listener {
        //        Плохо что в интерфейсе 3 метода???
        fun currentWeatherReady(currentWeather: CurrentWeather)
        fun hourlyForecastReady(hourlyForecasts: ArrayList<OneHourForecast>)
        fun dailyForecastReady(dailyForecast: ArrayList<OneDayForecast>)
    }

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecasts: ArrayList<OneHourForecast>
    private lateinit var dailyForecast: ArrayList<OneDayForecast>

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
                hourlyForecasts =
                    weatherJSONParser.parseHourlyWeather(response)
                listener.hourlyForecastReady(hourlyForecasts)
            }
            REQUEST_TYPE_VALUE_DAILY -> {
                dailyForecast =
                    weatherJSONParser.parseDailyWeather(response)
                listener.dailyForecastReady(dailyForecast)
            }
        }
    }
}