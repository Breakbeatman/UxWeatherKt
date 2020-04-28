package com.example.uxweatherkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.uxweatherkt.presenter.WeatherPresenterImpl
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.WeatherPresenter

class MainActivity : AppCompatActivity(), WeatherView {

    private lateinit var weatherPresenter: WeatherPresenter

    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherListFragment = WeatherListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment_container, weatherListFragment).commit()
//  TODO: init
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView2)

        weatherPresenter = WeatherPresenterImpl()
        weatherPresenter.attachView(this)

        button.setOnClickListener {
            weatherPresenter.currentWeatherViewIsReady()
            weatherPresenter.hourlyWeatherViewIsReady()
            weatherPresenter.dailyWeatherViewIsReady()
        }

    }

    override fun showWeather(message: String) {
        textView.text = message
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
}
