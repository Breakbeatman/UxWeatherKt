package com.example.uxweatherkt.weather

import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser

class WeatherModelImpl(
    private val weatherJSONParser: WeatherJSONParser,
    private val remoteRequestMaker: RemoteRequestMaker
) : WeatherModel {

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecast: ArrayList<HourForecast>
    private lateinit var dailyForecast: ArrayList<DayForecast>

    override fun loadCurrentWeatherBy(latitude: String, longitude: String): CurrentWeather? {
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT, latitude, longitude)
                ?: return null
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadCurrentWeatherBy(cityName: String): CurrentWeather? {
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT, cityName)
                ?: return null
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadHourlyForecastBy(
        latitude: String,
        longitude: String
    ): ArrayList<HourForecast>? {
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_HOURLY, latitude, longitude)
        hourlyForecast =
            weatherJSONParser.parseHourlyWeather(response!!)
        return hourlyForecast
    }

    override fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>? {
        TODO("Not yet implemented")
    }

    override fun loadDailyForecastBy(latitude: String, longitude: String): ArrayList<DayForecast>? {
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY, latitude, longitude)
                ?: return null
        dailyForecast =
            weatherJSONParser.parseDailyWeather(response)
        return dailyForecast
    }

    override fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>? {
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY, cityName)
                ?: return null
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