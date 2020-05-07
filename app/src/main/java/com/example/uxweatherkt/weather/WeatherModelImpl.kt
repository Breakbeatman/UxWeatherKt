package com.example.uxweatherkt.weather

import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser
import org.json.JSONObject

class WeatherModelImpl : WeatherModel {

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecast: ArrayList<HourForecast>
    private lateinit var dailyForecast: ArrayList<DayForecast>

    private var weatherJSONParser = WeatherJSONParser()
    private var remoteRequestMaker = RemoteRequestMaker()

    override fun loadCurrentWeather(): CurrentWeather{
        val response = remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT)
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadHourlyForecast(): ArrayList<HourForecast> {
        val response = remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_HOURLY)
        hourlyForecast =
            weatherJSONParser.parseHourlyWeather(response)
        return hourlyForecast
    }

    override fun loadDailyForecast(): ArrayList<DayForecast> {
        val response = remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY)
        dailyForecast =
            weatherJSONParser.parseDailyWeather(response)
        return dailyForecast
    }

//    fun onRequestReady(requestTypeValue: String, response: JSONObject) {
//        when (requestTypeValue) {
//            REQUEST_TYPE_VALUE_CURRENT -> {
//                currentWeather =
//                    weatherJSONParser.parseCurrentWeather(response)
//                listener.currentWeatherReady(currentWeather)
//            }
//            REQUEST_TYPE_VALUE_HOURLY -> {
//                hourlyForecast =
//                    weatherJSONParser.parseHourlyWeather(response)
//                listener.hourlyForecastReady(hourlyForecast)
//            }
//            REQUEST_TYPE_VALUE_DAILY -> {
//                dailyForecast =
//                    weatherJSONParser.parseDailyWeather(response)
//                listener.dailyForecastReady(dailyForecast)
//            }
//        }
//    }
}