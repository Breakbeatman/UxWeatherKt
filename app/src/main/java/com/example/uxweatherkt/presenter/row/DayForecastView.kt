package com.example.uxweatherkt.presenter.row

class DayForecastView(
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val maxTempFeelLike: String,
    val minTempFeelLike: String,
    val pressure: String,
    val humidity: String,
    val windSpeed: String,
    val iconId: Int
) {
    override fun toString(): String {
        return "DayForecastView(date='$date', maxTemp='$maxTemp', minTemp='$minTemp', maxTempFeelLike='$maxTempFeelLike', minTempFeelLike='$minTempFeelLike', pressure='$pressure', humidity='$humidity', windSpeed='$windSpeed', iconId=$iconId)"
    }
}
