package com.example.uxweatherkt.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenter
import com.example.uxweatherkt.ui.currentWeatherView.CurrentWeatherFragment
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListFragment

class RetainedFragment : Fragment() {
    // data object we want to retain
    var currentWeatherPresenter: CurrentWeatherPresenter? = null
    var dailyForecastPresenter: DailyForecastPresenter? = null

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