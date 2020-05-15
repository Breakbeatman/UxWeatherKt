package com.example.uxweatherkt.presenter.row

import android.os.Parcel

import android.os.Parcelable

class DayForecastRow : Parcelable {

    var date: String?
    var maxTemp: String?
    var minTemp: String?
    var maxTempFeelLike: String?
    var minTempFeelLike: String?
    var pressure: String?
    var humidity: String?
    var windSpeed: String?
    var iconId: Int

    constructor(
        date: String,
        maxTemp: String,
        minTemp: String,
        maxTempFeelLike: String,
        minTempFeelLike: String,
        pressure: String,
        humidity: String,
        windSpeed: String,
        iconId: Int
    ) {
        this.date = date
        this.maxTemp = maxTemp
        this.minTemp = minTemp
        this.maxTempFeelLike = maxTempFeelLike
        this.minTempFeelLike = minTempFeelLike
        this.pressure = pressure
        this.humidity = humidity
        this.windSpeed = windSpeed
        this.iconId = iconId
    }

    private constructor (parcel: Parcel) {
        val array = arrayOfNulls<String>(8)
        parcel.readStringArray(array)
        date = array[0]
        maxTemp = array[1]
        minTemp = array[2]
        maxTempFeelLike = array[3]
        minTempFeelLike = array[4]
        pressure = array[5]
        humidity = array[6]
        windSpeed = array[7]
        iconId = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeStringArray(
            arrayOf(
                date,
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