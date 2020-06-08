package com.example.uxweatherkt.presenter.row

import android.os.Parcel

import android.os.Parcelable

class DayForecastRow : Parcelable {

    var date: String?
    var dayOfWeek: String?
    var maxTemp: String?
    var minTemp: String?
    var maxTempFeelLike: String?
    var minTempFeelLike: String?
    var pressure: String?
    var humidity: String?
    var windSpeed: String?
    var windDir: String?
    var weatherDescription: String?
    var pop: String?
    var iconId: Int

    constructor(
        date: String,
        dayOfWeek: String,
        maxTemp: String,
        minTemp: String,
        maxTempFeelLike: String,
        minTempFeelLike: String,
        pressure: String,
        humidity: String,
        windSpeed: String,
        windDir: String,
        weatherDescription: String,
        pop: String,
        iconId: Int
    ) {
        this.date = date
        this.dayOfWeek = dayOfWeek
        this.maxTemp = maxTemp
        this.minTemp = minTemp
        this.maxTempFeelLike = maxTempFeelLike
        this.minTempFeelLike = minTempFeelLike
        this.pressure = pressure
        this.humidity = humidity
        this.windSpeed = windSpeed
        this.windDir = windDir
        this.weatherDescription = weatherDescription
        this.pop = pop
        this.iconId = iconId
    }

    private constructor (parcel: Parcel) {
        val array = arrayOfNulls<String>(11)
        parcel.readStringArray(array)
        date = array[0]
        dayOfWeek = array[1]
        maxTemp = array[2]
        minTemp = array[3]
        maxTempFeelLike = array[4]
        minTempFeelLike = array[5]
        pressure = array[6]
        humidity = array[7]
        windSpeed = array[8]
        windDir = array[9]
        pop = array[10]
        weatherDescription = array[11]
        iconId = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeStringArray(
            arrayOf(
                date,
                dayOfWeek,
                maxTemp,
                minTemp,
                maxTempFeelLike,
                minTempFeelLike,
                pressure,
                humidity,
                windSpeed
            )
        )
        dest?.writeInt(iconId)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "DayForecastView(date=$date, maxTemp=$maxTemp, minTemp=$minTemp, maxTempFeelLike=$maxTempFeelLike, minTempFeelLike=$minTempFeelLike, pressure=$pressure, humidity=$humidity, windSpeed=$windSpeed, iconId=$iconId)"
    }

    companion object CREATOR : Parcelable.Creator<DayForecastRow> {
        override fun createFromParcel(parcel: Parcel): DayForecastRow {
            return DayForecastRow(parcel)
        }

        override fun newArray(size: Int): Array<DayForecastRow?> {
            return arrayOfNulls(size)
        }
    }
}