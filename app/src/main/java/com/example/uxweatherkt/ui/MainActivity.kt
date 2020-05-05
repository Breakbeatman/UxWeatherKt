package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.uxweatherkt.presenter.WeatherPresenterImpl
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.RetainedFragment
import com.example.uxweatherkt.presenter.WeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.presenter.row.DayForecastView

class MainActivity : AppCompatActivity(), WeatherView, DailyForecastListFragment.Listener {

    private lateinit var weatherPresenter: WeatherPresenter
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
            weatherPresenter = WeatherPresenterImpl()

            supportFragmentManager.beginTransaction().add(retainedFragment!!, "weatherPresenter")
                .commit()
            retainedFragment!!.weatherPresenter = weatherPresenter
        }
        weatherPresenter = retainedFragment!!.weatherPresenter!!

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main__fragment_container, CurrentWeatherFragment(), "fr1")
                .commit()
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main__fragment_container2, DailyForecastListFragment(), "fr2")
                .commit()
        }

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
        val currentWeatherFragment =
            supportFragmentManager.findFragmentByTag("fr1") as CurrentWeatherFragment
        currentWeatherFragment.initData(currentWeatherView)
    }

    private fun dailyForecastDataLoaded(dailyForecastView: ArrayList<DayForecastView>) {
        val dailyForecastListFragment =
            supportFragmentManager.findFragmentByTag("fr2") as DailyForecastListFragment
        dailyForecastListFragment.initData(dailyForecastView)
    }

    private fun saveData() {
        retainedFragment!!.weatherPresenter = weatherPresenter
    }

    // нажатие на DayForecast
    override fun onDailyForecastListItemClicked() {
        TODO("Not yet implemented")
    }
}
