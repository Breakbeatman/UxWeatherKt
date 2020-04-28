package com.example.uxweatherkt.weather.util

import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.OneDayForecast
import com.example.uxweatherkt.weather.model.OneHourForecast
import com.example.uxweatherkt.weather.model.Weather
import org.json.JSONObject

class WeatherJSONParser {

    fun parseCurrentWeather(jsonWeather: JSONObject): CurrentWeather {
        val weather = parseWeather(jsonWeather.getJSONObject(PARSER_KEY_WEATHER))
        val pod = jsonWeather.getString(PARSER_KEY_POD)
        val temp = jsonWeather.getDouble(PARSER_KEY_TEMP)
        val feelLike = jsonWeather.getDouble(PARSER_KEY_FEEL_LIKE)
        val pressure = jsonWeather.getDouble(PARSER_KEY_PRESSURE)
        val humidity = jsonWeather.getDouble(PARSER_KEY_HUMIDITY)
        val windSpeed = jsonWeather.getDouble(PARSER_KEY_WIND_SPEED)
        return CurrentWeather(weather, pod, temp, feelLike, pressure, humidity, windSpeed)
    }

    fun parseHourlyWeather(jsonWeather: JSONObject): ArrayList<OneHourForecast> {
        val hourForecasts = ArrayList<OneHourForecast>()
        val hourlyForecastsJSON = jsonWeather.getJSONArray(PARSER_KEY_LIST)
        for (i in 0 until hourlyForecastsJSON.length()) {
            val hourlyForecastJSON =  hourlyForecastsJSON.getJSONObject(i)
            val weather =
                parseWeather(hourlyForecastJSON.getJSONObject(PARSER_KEY_WEATHER))
            val pod = hourlyForecastJSON.getString(PARSER_KEY_POD)
            val date = hourlyForecastJSON.getLong(PARSER_KEY_DATE)
            val eve = hourlyForecastJSON.getDouble(PARSER_KEY_EVE)
            val pressure = hourlyForecastJSON.getDouble(PARSER_KEY_PRESSURE)
            val humidity = hourlyForecastJSON.getDouble(PARSER_KEY_HUMIDITY)
            val windSpeed = hourlyForecastJSON.getDouble(PARSER_KEY_WIND_SPEED)
            val oneHourForecast =
                OneHourForecast(weather, pod, date, eve, pressure, humidity, windSpeed)
            hourForecasts.add(oneHourForecast)
        }
        return hourForecasts
    }

    fun parseDailyWeather(jsonWeather: JSONObject): ArrayList<OneDayForecast> {
        val dailyForecasts = ArrayList<OneDayForecast>()
        val dailyForecastsJSON = jsonWeather.getJSONArray(PARSER_KEY_LIST)
        for (i in 0 until dailyForecastsJSON.length()) {
            val dailyForecastJSON = dailyForecastsJSON.getJSONObject(i)
            val weather =
                parseWeather(dailyForecastJSON.getJSONObject(PARSER_KEY_WEATHER))
            val date = dailyForecastJSON.getLong(PARSER_KEY_DATE)
            val eve = dailyForecastJSON.getDouble(PARSER_KEY_EVE)
            val pressure = dailyForecastJSON.getDouble(PARSER_KEY_PRESSURE)
            val humidity = dailyForecastJSON.getDouble(PARSER_KEY_HUMIDITY)
            val windSpeed = dailyForecastJSON.getDouble(PARSER_KEY_WIND_SPEED)
            val oneDayForecast = OneDayForecast(weather, date, eve, pressure, humidity, windSpeed)
            dailyForecasts.add(oneDayForecast)
        }
        return dailyForecasts
    }

    private fun parseWeather(weather: JSONObject): Weather {
        return Weather(weather.getInt(PARSER_KEY_CODE), weather.getString(PARSER_KEY_DESCRIPTION))
    }
}