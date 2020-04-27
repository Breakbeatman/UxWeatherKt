package com.example.uxweatherkt.model

class Weather constructor(

        private var temperature: String
       /* var city: String,
        var country: String,
        var date: Date,
        var wind: String,
        var pressure: String,
        var humidity: String*/
    )

    {
        var getTemperature
            set(value) {temperature = value}
            get() = temperature
        /*var getCity: String
            set(value) {city = value}
            get() = city
        var getCountry: String
            set(value) {country = value}
            get() = country

        var getWind: String
            set(value) {wind = value}
            get() = wind
        var getPressure: String
            set(value) {pressure = value}
            get() = pressure
        var getHumidity: String
            set(value) {humidity = value}
            get() = humidity
        var getDate: Date
            set(value) {date = value}
            get() = date*/
    }

