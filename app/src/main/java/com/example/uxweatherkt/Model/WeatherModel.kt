package com.example.uxweatherkt.Model

import com.example.uxweatherkt.Contract

class WeatherModel : Contract.WeatherModel, RemoteRequestMaker.listener{

    private lateinit var remoteRequestMaker: RemoteRequestMaker

    override fun loadWeather(): Weather {
        remoteRequestMaker = RemoteRequestMaker(this)
        remoteRequestMaker.makeRequest()
        return Weather("HUI")
        //return Weather(remoteRequestMaker.makeRequest().getString("city"))
    }

    override fun onRequestReady() {
        println(remoteRequestMaker.getJSONObject())
    }

}