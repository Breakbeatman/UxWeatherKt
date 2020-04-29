package com.example.uxweatherkt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.CurrentWeatherView

class CurrentWeatherFragment : Fragment() {

    private lateinit var tvTemp: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    fun initData(currentWeatherView: CurrentWeatherView) {
        tvTemp = activity!!.findViewById(R.id.fragment_current_weather_tv_temp)
        tvPressure = activity!!.findViewById(R.id.fragment_current_weather_tv_pressure)
        tvHumidity = activity!!.findViewById(R.id.fragment_current_weather_tv_humidity)
        tvWindSpeed = activity!!.findViewById(R.id.fragment_current_weather_tv_wind_speed)
        tvTemp.text = currentWeatherView.temp
        tvPressure.text = currentWeatherView.pressure
        tvHumidity.text = currentWeatherView.humidity
        tvWindSpeed.text = currentWeatherView.windSpeed
    }
}
