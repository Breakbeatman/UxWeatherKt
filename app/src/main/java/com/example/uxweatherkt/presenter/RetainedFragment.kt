package com.example.uxweatherkt.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uxweatherkt.ui.CurrentWeatherFragment
import com.example.uxweatherkt.ui.DailyForecastListFragment

class RetainedFragment : Fragment() {
    // data object we want to retain
    var weatherPresenter: WeatherPresenter? = null
    var currentWeatherFragment: CurrentWeatherFragment? = null
    var dailyForecastListFragment: DailyForecastListFragment? = null

    // this method is only called once for this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // retain this fragment
        retainInstance = true
    }

 /*   fun setData(weatherPresenter: WeatherPresenter?) {
        this.weatherPresenter = weatherPresenter
    }

    fun getData(): WeatherPresenter? {
        return weatherPresenter
    }*/
}