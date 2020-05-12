package com.example.uxweatherkt.weather

import android.util.Log
import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

import com.example.uxweatherkt.weather.repository.RemoteRequestMaker
import com.example.uxweatherkt.weather.util.WeatherJSONParser
import org.json.JSONObject

class WeatherModelImpl(private val weatherJSONParser: WeatherJSONParser) : WeatherModel {

    private lateinit var currentWeather: CurrentWeather
    private lateinit var hourlyForecast: ArrayList<HourForecast>
    private lateinit var dailyForecast: ArrayList<DayForecast>

    private lateinit var cRemoteRequestMaker: RemoteRequestMaker
    private lateinit var hRemoteRequestMaker: RemoteRequestMaker
    private lateinit var dRemoteRequestMaker: RemoteRequestMaker
    private lateinit var remoteRequestMaker: RemoteRequestMaker

    override fun loadCurrentWeather(latitude: String, longitude: String): CurrentWeather? {
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        remoteRequestMaker = RemoteRequestMaker()
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT, latitude, longitude)
                ?: return null
        currentWeather =
            weatherJSONParser.parseCurrentWeather(response)
        return currentWeather
    }

    override fun loadHourlyForecast(latitude: String, longitude: String): ArrayList<HourForecast>? {
        remoteRequestMaker = RemoteRequestMaker()
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_HOURLY, latitude, longitude)
        hourlyForecast =
            weatherJSONParser.parseHourlyWeather(response!!)
        return hourlyForecast
    }

    override fun loadDailyForecast(latitude: String, longitude: String): ArrayList<DayForecast>? {
        Log.d("TAG", "THREAD NOW" + Thread.currentThread().name)
        remoteRequestMaker = RemoteRequestMaker()
        val response =
            remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY, latitude, longitude)
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