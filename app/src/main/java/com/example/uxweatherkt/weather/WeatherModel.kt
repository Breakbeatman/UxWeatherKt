package com.example.uxweatherkt.weather

import com.example.uxweatherkt.Contract
import com.example.uxweatherkt.REQUEST_TYPE_VALUE_CURRENT
import com.example.uxweatherkt.REQUEST_TYPE_VALUE_DAILY
import com.example.uxweatherkt.REQUEST_TYPE_VALUE_HOURLY
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DailyWeather
import com.example.uxweatherkt.weather.model.HourlyWeather
import com.example.uxweatherkt.weather.repository.RemoteRequestMaker

class WeatherModel : Contract.WeatherModel, RemoteRequestMaker.Listener {

    private lateinit var remoteRequestMaker: RemoteRequestMaker

    override fun loadCurrentWeather(): CurrentWeather {
        remoteRequestMaker = RemoteRequestMaker(this)
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_CURRENT)
        return CurrentWeather("HUI")
    }

    override fun loadHourlyWeather(): HourlyWeather {
        remoteRequestMaker = RemoteRequestMaker(this)
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_HOURLY)
        return HourlyWeather()
    }

    override fun loadDailyWeather(): DailyWeather {
        remoteRequestMaker = RemoteRequestMaker(this)
        remoteRequestMaker.makeRequest(REQUEST_TYPE_VALUE_DAILY)
        return DailyWeather()
    }

    override fun onRequestReady() {
        println(remoteRequestMaker.jsonObject)
    }
}