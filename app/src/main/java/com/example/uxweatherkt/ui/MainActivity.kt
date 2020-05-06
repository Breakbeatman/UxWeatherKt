package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.RetainedFragment
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherDataBinder
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastDataBinder
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.currentWeatherView.CurrentWeatherFragment
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListFragment
import com.example.uxweatherkt.weather.WeatherModel

class MainActivity : AppCompatActivity() {

    private lateinit var weatherModel: WeatherModel
    private lateinit var currentWeatherPresenter: CurrentWeatherPresenter
    private lateinit var dailyForecastPresenter: DailyForecastPresenter
    private lateinit var currentWeatherDataBinder: CurrentWeatherDataBinder
    private lateinit var dailyForecastDataBinder: DailyForecastDataBinder
    private var retainedFragment: RetainedFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  TODO: init

//                Получаем данные от retained fragment
        retainedFragment =
            supportFragmentManager.findFragmentByTag("weatherPresenter") as? RetainedFragment

        if (retainedFragment == null) {
            retainedFragment = RetainedFragment()
            supportFragmentManager.beginTransaction().add(retainedFragment!!, "weatherPresenter")
                .commit()
//            currentWeatherPresenter =
//                CurrentWeatherPresenterImpl(weatherModel, currentWeatherDataBinder)
//            dailyForecastPresenter =
//                DailyForecastPresenterImpl(weatherModel, dailyWeatherDataBinder)

//            retainedFragment!!.currentWeatherPresenter = currentWeatherPresenter
//            retainedFragment!!.dailyForecastPresenter = dailyForecastPresenter
        }
//        currentWeatherPresenter = retainedFragment!!.currentWeatherPresenter!!
//        dailyForecastPresenter = retainedFragment!!.dailyForecastPresenter!!

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

//        currentWeatherPresenter.attachView(this)
//        dailyForecastPresenter.attachView(this)

//        currentWeatherPresenter.getLiveData().observe(this, Observer
//        { currentWeatherView -> currentWeatherDataLoaded(currentWeatherView) })

//        dailyForecastPresenter.getLiveData().observe(this, Observer
//        { dailyForecastView -> dailyForecastDataLoaded(dailyForecastView as ArrayList<DayForecastView>) })
    }

    override fun onDestroy() {
        super.onDestroy()
//        currentWeatherPresenter.detachView()
//        dailyForecastPresenter.detachView()
        saveData()
    }

    private fun currentWeatherDataLoaded(currentWeatherView: CurrentWeatherView) {
//        val currentWeatherFragment =
//            supportFragmentManager.findFragmentByTag("fr1") as CurrentWeatherFragment
//        currentWeatherFragment.initData(currentWeatherView)
    }

    private fun dailyForecastDataLoaded(dailyForecastView: ArrayList<DayForecastView>) {
//        val dailyForecastListFragment =
//            supportFragmentManager.findFragmentByTag("fr2") as DailyForecastListFragment
//        dailyForecastListFragment.initData(dailyForecastView)
    }

    private fun saveData() {
//        retainedFragment!!.currentWeatherPresenter = currentWeatherPresenter
//        retainedFragment!!.dailyForecastPresenter = dailyForecastPresenter
    }
}
