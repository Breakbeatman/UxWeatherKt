package com.example.uxweatherkt.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.uxweatherkt.Contract
import com.example.uxweatherkt.Presenter.WeatherPresenter
import com.example.uxweatherkt.R

class MainActivity : AppCompatActivity(), Contract.WeatherView {

    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var weatherPresenter: Contract.WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherListFragment = WeatherListFragment()
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, weatherListFragment).commit()

        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView2)
        weatherPresenter = WeatherPresenter (this)

        button.setOnClickListener {
               weatherPresenter.requestToUpdate()
        }

    }

    override fun showWeather(message: String) {
        textView.setText(message)
    }

}
