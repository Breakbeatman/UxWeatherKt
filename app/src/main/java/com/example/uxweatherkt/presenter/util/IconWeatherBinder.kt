package com.example.uxweatherkt.presenter.util

import com.example.uxweatherkt.R

class IconWeatherBinder {

    fun bindIconId(code: Int, pod: String): Int {
        val iconName = bindWeatherCode(code, pod)
        return selectIcon(iconName)
    }

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
            else -> "1000$pod"
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
            else -> R.drawable.unknow_weather_1000d
        }
    }
}