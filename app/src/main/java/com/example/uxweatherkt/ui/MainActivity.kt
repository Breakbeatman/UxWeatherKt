package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.uxweatherkt.presenter.WeatherPresenterImpl
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView

class MainActivity : AppCompatActivity(), WeatherView {

    private lateinit var weatherPresenter: WeatherPresenter
    private lateinit var currentWeatherFragment: CurrentWeatherFragment
    private lateinit var dailyForecastListFragment: DailyForecastListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  TODO: init
        currentWeatherFragment = CurrentWeatherFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container, currentWeatherFragment).commit()

        dailyForecastListFragment = DailyForecastListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container2, dailyForecastListFragment).commit()


        weatherPresenter = WeatherPresenterImpl()
        weatherPresenter.attachView(this)
        weatherPresenter.getCurrentLiveData().observe(this, Observer
        { currentWeatherView -> currentWeatherDataLoaded(currentWeatherView) })
        weatherPresenter.currentWeatherViewIsReady()
        weatherPresenter.getDailyLiveData().observe(this, Observer
        { dailyForecastView -> dailyForecastDataLoaded(dailyForecastView as ArrayList<DayForecastView>) })
    }

    override fun showWeather(message: String) {

    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherPresenter.detachView()
    }

    private fun currentWeatherDataLoaded(currentWeatherView: CurrentWeatherView) {
        currentWeatherFragment.initData(currentWeatherView)
    }

    private fun dailyForecastDataLoaded(dailyForecastView: ArrayList<DayForecastView>) {
        dailyForecastListFragment.initData(dailyForecastView)
    }
}
