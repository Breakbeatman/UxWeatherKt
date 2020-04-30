package com.example.uxweatherkt.ui

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.CurrentWeatherView

class CurrentWeatherFragment : Fragment() {

    private lateinit var tvTemp: TextView
    private lateinit var tvFeelLike: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDescription: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    fun initData(currentWeatherView: CurrentWeatherView) {
        tvTemp = activity!!.findViewById(R.id.fragment_current_weather_tv_temp)
        tvFeelLike = activity!!.findViewById(R.id.fragment_current_weather_tv_feel_like)
        tvPressure = activity!!.findViewById(R.id.fragment_current_weather_tv_pressure)
        tvHumidity = activity!!.findViewById(R.id.fragment_current_weather_tv_humidity)
        tvWindSpeed = activity!!.findViewById(R.id.fragment_current_weather_tv_wind_speed)
        tvDescription = activity!!.findViewById(R.id.fragment_current_weather_tv_description)
        ivDescription = activity!!.findViewById(R.id.fragment_current_weather_iv_description)
        tvTemp.text = currentWeatherView.temp
        tvFeelLike.text = currentWeatherView.feelLike
        tvPressure.text = currentWeatherView.pressure
        tvHumidity.text = currentWeatherView.humidity
        tvWindSpeed.text = currentWeatherView.windSpeed
        tvDescription.text = currentWeatherView.weatherDescription
        ivDescription.setImageResource(currentWeatherView.iconId)
    }
}
