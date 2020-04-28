package com.example.uxweatherkt.weather.model

class Weather(val code: Int, val description: String) {
    override fun toString(): String {
        return "Weather(code=$code, description='$description')"
    }
}