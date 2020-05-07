package com.example.uxweatherkt.ui.currentWeatherView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.uxweatherkt.App
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherViewModel
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenterImpl
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.ui.WeatherView


class CurrentWeatherFragment : Fragment(), WeatherView {

    private var currentWeatherPresenter: CurrentWeatherPresenter? = null


    private lateinit var liveData: MutableLiveData<CurrentWeatherView>

    private lateinit var tvTemp: TextView
    private lateinit var tvFeelLike: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDescription: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CurrentWeatherViewModel =
            ViewModelProviders.of(this)
                .get(CurrentWeatherViewModel::class.java)
        currentWeatherPresenter = viewModel.currentWeatherPresenter
        if (currentWeatherPresenter == null) {
            val weatherModel =
                (activity!!.application as App).getDependencyRoot().weatherModel
            val currentWeatherDataBinder =
                (activity!!.application as App).getDependencyRoot().currentWeatherDataBinder
            currentWeatherPresenter =
                CurrentWeatherPresenterImpl(weatherModel, currentWeatherDataBinder)
        }
        currentWeatherPresenter!!.attachView(this)
        liveData = currentWeatherPresenter!!.getLiveData()
        liveData.observe(this, Observer { showWeather() })
        currentWeatherPresenter!!.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        currentWeatherPresenter!!.detachView()
    }

    override fun showWeather() {
        val currentWeatherView = liveData.value
        initData(currentWeatherView)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun initData(currentWeatherView: CurrentWeatherView?) {
        tvTemp = activity!!.findViewById(R.id.fragment_current_weather__tvTemp)
        tvFeelLike = activity!!.findViewById(R.id.fragment_current_weather__tvFeelLike)
        tvPressure = activity!!.findViewById(R.id.fragment_current_weather__tvPressure)
        tvHumidity = activity!!.findViewById(R.id.fragment_current_weather__tvHumidity)
        tvWindSpeed = activity!!.findViewById(R.id.fragment_current_weather__tvWindSpeed)
        tvDescription = activity!!.findViewById(R.id.fragment_current_weather__tvDescription)
        ivDescription = activity!!.findViewById(R.id.fragment_current_weather__ivDescription)
        tvTemp.text = currentWeatherView!!.temp
        tvFeelLike.text = currentWeatherView.feelLike
        tvPressure.text = currentWeatherView.pressure
        tvHumidity.text = currentWeatherView.humidity
        tvWindSpeed.text = currentWeatherView.windSpeed
        tvDescription.text = currentWeatherView.weatherDescription
        ivDescription.setImageResource(currentWeatherView.iconId)
    }
}