package com.example.uxweatherkt.weather.util

import android.util.Log
import com.example.uxweatherkt.*
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast
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
        val windDir = jsonWeather.getString(PARSER_KEY_WIND_DIR)
        val uvIndex = jsonWeather.getInt(PARSER_KEY_UV_INDEX)
        val visibility = jsonWeather.getInt(PARSER_KEY_VISIBILITY)
        val dewPoint = jsonWeather.getDouble(PARSER_KEY_DEW_POINT)
        return CurrentWeather(weather, pod, temp, feelLike, pressure, humidity, windSpeed, windDir, uvIndex, visibility, dewPoint)
    }

    fun parseHourlyWeather(jsonWeather: JSONObject): ArrayList<HourForecast> {
        val hourForecasts = ArrayList<HourForecast>()
        val hourlyForecastsJSON = jsonWeather.getJSONArray(PARSER_KEY_LIST)
        for (i in 0 until hourlyForecastsJSON.length()) {
            val hourlyForecastJSON = hourlyForecastsJSON.getJSONObject(i)
            val weather =
                parseWeather(hourlyForecastJSON.getJSONObject(PARSER_KEY_WEATHER))
            val pod = hourlyForecastJSON.getString(PARSER_KEY_POD)
            val date = hourlyForecastJSON.getLong(PARSER_KEY_DATE)
            val temp = hourlyForecastJSON.getDouble(PARSER_KEY_TEMP)
            val feelLike = hourlyForecastJSON.getDouble(PARSER_KEY_FEEL_LIKE)
            val pressure = hourlyForecastJSON.getDouble(PARSER_KEY_PRESSURE)
            val humidity = hourlyForecastJSON.getDouble(PARSER_KEY_HUMIDITY)
            val windSpeed = hourlyForecastJSON.getDouble(PARSER_KEY_WIND_SPEED)
            val oneHourForecast =
                HourForecast(weather, pod, date, temp, feelLike, pressure, humidity, windSpeed)
            hourForecasts.add(oneHourForecast)
        }
        return hourForecasts
    }

    fun parseDailyWeather(jsonWeather: JSONObject): ArrayList<DayForecast> {
        val dailyForecasts = ArrayList<DayForecast>()
        val dailyForecastsJSON = jsonWeather.getJSONArray(PARSER_KEY_LIST)
        for (i in 0 until dailyForecastsJSON.length()) {
            val dailyForecastJSON = dailyForecastsJSON.getJSONObject(i)
            val weather =
                parseWeather(dailyForecastJSON.getJSONObject(PARSER_KEY_WEATHER))
            val date = dailyForecastJSON.getLong(PARSER_KEY_DATE)
            val maxTemp = dailyForecastJSON.getDouble(PARSER_KEY_MAX_TEMP)
            val minTemp = dailyForecastJSON.getDouble(PARSER_KEY_MIN_TEMP)
            val maxTempFeelLike = dailyForecastJSON.getDouble(PARSER_KEY_FEEL_LIKE_MAX_TEMP)
            val minTempFeelLike = dailyForecastJSON.getDouble(PARSER_KEY_FEEL_LIKE_MIX_TEMP)
            val pressure = dailyForecastJSON.getDouble(PARSER_KEY_PRESSURE)
            val humidity = dailyForecastJSON.getDouble(PARSER_KEY_HUMIDITY)
            val windSpeed = dailyForecastJSON.getDouble(PARSER_KEY_WIND_SPEED)
            val oneDayForecast = DayForecast(
                weather,
                date,
                maxTemp,
                minTemp,
                maxTempFeelLike,
                minTempFeelLike,
                pressure,
                humidity,
                windSpeed
            )
            dailyForecasts.add(oneDayForecast)
        }
        return dailyForecasts
    }

    private fun parseWeather(weather: JSONObject): Weather {
        return Weather(weather.getInt(PARSER_KEY_CODE), weather.getString(PARSER_KEY_DESCRIPTION))
    }
}