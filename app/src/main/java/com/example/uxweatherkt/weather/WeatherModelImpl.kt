package com.example.uxweatherkt.weather

import com.example.uxweatherkt.*
import com.example.uxweatherkt.presenter.util.Coordinates
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

import com.example.uxweatherkt.weather.repository.HttpRequestExecutor
import com.example.uxweatherkt.weather.util.WeatherJSONParser

class WeatherModelImpl(
    private val weatherJSONParser: WeatherJSONParser,
    private val httpRequestExecutor: HttpRequestExecutor
) : WeatherModel {

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecast: ArrayList<HourForecast>
    private lateinit var dailyForecast: ArrayList<DayForecast>

    override fun loadCurrentWeatherBy(coordinates: Coordinates): CurrentWeather? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_CURRENT, coordinates)
                ?: return null
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadCurrentWeatherBy(cityName: String): CurrentWeather? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_CURRENT, cityName)
                ?: return null
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadHourlyForecastBy(coordinates: Coordinates): ArrayList<HourForecast>? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_HOURLY, coordinates)
                ?: return null
        hourlyForecast =
            weatherJSONParser.parseHourlyWeather(response)
        return hourlyForecast
    }

    override fun loadHourlyForecastBy(cityName: String): ArrayList<HourForecast>? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_HOURLY, cityName)
                ?: return null
        hourlyForecast =
            weatherJSONParser.parseHourlyWeather(response)
        return hourlyForecast
    }

    override fun loadDailyForecastBy(coordinates: Coordinates): ArrayList<DayForecast>? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_DAILY, coordinates)
                ?: return null
        dailyForecast =
            weatherJSONParser.parseDailyWeather(response)
        return dailyForecast
    }

    override fun loadDailyForecastBy(cityName: String): ArrayList<DayForecast>? {
        val response =
            httpRequestExecutor.makeRequest(REQUEST_TYPE_VALUE_DAILY, cityName)
                ?: return null
        dailyForecast =
            weatherJSONParser.parseDailyWeather(response)
        return dailyForecast
    }
}