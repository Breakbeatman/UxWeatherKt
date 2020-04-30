package com.example.uxweatherkt.presenter

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.presenter.row.HourForecastView
import com.example.uxweatherkt.weather.model.CurrentWeather
import com.example.uxweatherkt.weather.model.DayForecast
import com.example.uxweatherkt.weather.model.HourForecast

class DataBinder {

    fun bindCurrentWeatherView(currentWeather: CurrentWeather): CurrentWeatherView {
        val temp = if (currentWeather.temp < 0) {
            currentWeather.temp.toString()
        } else {
            "+" + currentWeather.temp.toString()
        }
        val feelLike = currentWeather.feelLike.toString()
        val pressure = currentWeather.pressure.toString()
        val humidity = currentWeather.humidity.toString()
        val windSpeed = currentWeather.windSpeed.toString()
        val weatherDescription = currentWeather.weather.description
        val iconId = bindIconId(currentWeather.weather.code, currentWeather.pod)
        return CurrentWeatherView(
            temp,
            feelLike,
            pressure,
            humidity,
            windSpeed,
            weatherDescription,
            iconId
        )
    }

    fun bindHourlyForecastView(hourlyForecast: ArrayList<HourForecast>): ArrayList<HourForecastView> {
        val hourlyForecastView = ArrayList<HourForecastView>()
        for (i in hourlyForecast.indices) {
            val date = hourlyForecast[i].date.toString()
            val eveTemp = if (hourlyForecast[i].eveTemp < 0) {
                hourlyForecast[i].eveTemp.toString()
            } else {
                "+" + hourlyForecast[i].eveTemp.toString()
            }
            val pressure: String = hourlyForecast[i].pressure.toString()
            val humidity: String = hourlyForecast[i].humidity.toString()
            val windSpeed: String = hourlyForecast[i].windSpeed.toString()
            val iconId: Int = bindIconId(hourlyForecast[i].weather.code, hourlyForecast[i].pod)
            hourlyForecastView.add(
                HourForecastView(
                    date,
                    eveTemp,
                    pressure,
                    humidity,
                    windSpeed,
                    iconId
                )
            )
        }
        return hourlyForecastView
    }

    fun bindDailyForecastView(dailyForecast: ArrayList<DayForecast>): ArrayList<DayForecastView> {
        val dailyForecastView = ArrayList<DayForecastView>()
        for (i in dailyForecast.indices) {
            val date = dailyForecast[i].date.toString()
            val eveTemp = if (dailyForecast[i].eveTemp < 0) {
                dailyForecast[i].eveTemp.toString()
            } else {
                "+" + dailyForecast[i].eveTemp.toString()
            }
            val pressure: String = dailyForecast[i].pressure.toString()
            val humidity: String = dailyForecast[i].humidity.toString()
            val windSpeed: String = dailyForecast[i].windSpeed.toString()
            val iconId: Int = bindIconId(dailyForecast[i].weather.code, "d")
            dailyForecastView.add(
                DayForecastView(
                    date,
                    eveTemp,
                    pressure,
                    humidity,
                    windSpeed,
                    iconId
                )
            )
        }
        return dailyForecastView
    }

    private fun bindIconId(code: Int, pod: String): Int {
        val iconName = bindWeatherCode(code, pod)
        return selectIcon(iconName)
    }

    //    TODO:
    private fun bindWeatherCode(code: Int, pod: String): String {
        return when (code) {
            //            //thunderstorm
            in 200..233 -> "200$pod"
            //            //rain
            in 300..321, in 500..504, 511 -> "300$pod"
            //            //shower rain
            520, 521, 522, 531 -> "500$pod"
            //            //snow
            in 600..623 -> "600$pod"
            //            //mist
            700, 711, 721, 731, 741, 751, 761, 762, 771, 781 -> "700$pod"
            //            //clear sky
            800 -> "800$pod"
            //            //few clouds
            801, 802 -> "8012$pod"
            //            //scattered clouds
            803 -> "803$pod"
            //            //broken clouds
            804 -> "804$pod"
            //            //unknown weather
            else -> "1000"
        }.toString()
    }

    private fun selectIcon(iconName: String): Int {
        return when (iconName) {
            "200d" -> R.drawable.thunderstorm_200d
            "200n" -> R.drawable.thunderstorm_200n
            "300d" -> R.drawable.rain_300d
            "300n" -> R.drawable.rain_300n
            "500d" -> R.drawable.shower_rain_500d
            "500n" -> R.drawable.shower_rain_500n
            "600d" -> R.drawable.snow_600d
            "600n" -> R.drawable.snow_600n
            "700d" -> R.drawable.mist_700d
            "700n" -> R.drawable.mist_700d
            "800d" -> R.drawable.clear_sky_800d
            "800n" -> R.drawable.clear_sky_800n
            "8012d" -> R.drawable.few_clouds_8012d
            "8012n" -> R.drawable.few_clouds_8012n
            "803d" -> R.drawable.scattered_clouds_803d
            "803n" -> R.drawable.scattered_clouds_803n
            "804d" -> R.drawable.brocken_clouds_804d
            "804n" -> R.drawable.brocken_clouds_804n
//            TODO: нет картинки неизвестного кода
            else -> 1000
        }
    }
}