package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastDataBinder
import com.example.uxweatherkt.ui.currentWeatherView.CurrentWeatherFragment
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListFragment
import com.example.uxweatherkt.weather.WeatherModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  TODO: init

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.activity_main__fragment_container,
                    CurrentWeatherFragment(), "fr1"
                )
                .commit()
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.activity_main__fragment_container2,
                    DailyForecastListFragment(), "fr2"
                )
                .commit()
        }
    }
}
