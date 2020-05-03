package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.uxweatherkt.presenter.WeatherPresenterImpl
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.RetainedFragment
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView

class MainActivity : AppCompatActivity(), WeatherView, DailyForecastListFragment.Listener {

    private lateinit var weatherPresenter: WeatherPresenter
    private var currentWeatherFragment: CurrentWeatherFragment? = null
    private var dailyForecastListFragment: DailyForecastListFragment? = null
    private var retainedFragment: RetainedFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  TODO: init

//        currentWeatherFragment =
//            supportFragmentManager.findFragmentById(R.id.activity_main_fragment_1) as CurrentWeatherFragment
//        dailyForecastListFragment =
//            supportFragmentManager.findFragmentById(R.id.activity_main_fragment_2) as DailyForecastListFragment

//        currentWeatherFragment = CurrentWeatherFragment()
//        dailyForecastListFragment = DailyForecastListFragment()
//                Получаем данные от retained fragment
        retainedFragment =
            supportFragmentManager.findFragmentByTag("weatherPresenter") as? RetainedFragment
        currentWeatherFragment = CurrentWeatherFragment()
        dailyForecastListFragment = DailyForecastListFragment()

        if (retainedFragment == null) {
            retainedFragment = RetainedFragment()
            weatherPresenter = WeatherPresenterImpl()

            supportFragmentManager.beginTransaction().add(retainedFragment!!, "weatherPresenter")
                .commit()
            retainedFragment!!.weatherPresenter = weatherPresenter
        }
        weatherPresenter = retainedFragment!!.weatherPresenter!!
//          По тэгу:

        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container, currentWeatherFragment!!).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container2, dailyForecastListFragment!!).commit()

//        currentWeatherFragment = supportFragmentManager.findFragmentByTag("CurrentWeatherFragment") as? CurrentWeatherFragment
//        dailyForecastListFragment = supportFragmentManager.findFragmentByTag("DailyWeatherFragment") as? DailyForecastListFragment

        weatherPresenter.attachView(this)
        weatherPresenter.getCurrentLiveData().observe(this, Observer
        { currentWeatherView -> currentWeatherDataLoaded(currentWeatherView) })
        weatherPresenter.currentWeatherViewIsReady()
        weatherPresenter.getDailyLiveData().observe(this, Observer
        { dailyForecastView -> dailyForecastDataLoaded(dailyForecastView as ArrayList<DayForecastView>) })
        weatherPresenter.dailyWeatherViewIsReady()
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
        saveData()
    }

    private fun currentWeatherDataLoaded(currentWeatherView: CurrentWeatherView) {
        currentWeatherFragment!!.initData(currentWeatherView)
    }

    private fun dailyForecastDataLoaded(dailyForecastView: ArrayList<DayForecastView>) {
        dailyForecastListFragment!!.initData(dailyForecastView)
    }

    private fun saveData() {
        retainedFragment!!.weatherPresenter = weatherPresenter
    }

    // нажатие на DayForecast
    override fun onDailyForecastListItemClicked() {
        TODO("Not yet implemented")
    }
}
