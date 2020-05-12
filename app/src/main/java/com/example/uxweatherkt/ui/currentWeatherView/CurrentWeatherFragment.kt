package com.example.uxweatherkt.ui.currentWeatherView

import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
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
import com.example.uxweatherkt.presenter.util.LocationFinder
import com.example.uxweatherkt.ui.WeatherView


class CurrentWeatherFragment : Fragment(),
    WeatherView {

    private var currentWeatherPresenter: CurrentWeatherPresenter? = null

    private lateinit var liveData: MutableLiveData<CurrentWeatherView>

    private lateinit var tvDataIsNotAvailable: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvFeelLike: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDescription: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        presenter initialization and attach with WeatherView (this fragment)
        initPresenter()
        liveData = currentWeatherPresenter!!.getLiveData()
        liveData.observe(this, Observer { showWeather() })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_current_weather, container, false)
//        initialization of all views in layout (fragment_current_weather)
        init(view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        currentWeatherPresenter!!.detachView()
    }

    override fun showWeather() {
        hideLoading()
        if (liveData.value == null) {
            dataIsNotAvailable()
            return
        }
        initData(liveData.value)
    }

    override fun dataIsNotAvailable() {
        val stringing = "DATA IS NOT AVAILABLE"
        tvDataIsNotAvailable =
            view!!.findViewById((R.id.fragment_current_weather__tvDataIsNotAvailable))
        tvDataIsNotAvailable.text = stringing
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    fun onLocationReady(location: Location) {
        progressBar = view!!.findViewById(R.id.fragment_current_weather__pbLoading)
        showLoading()
        currentWeatherPresenter!!.getData(location)
    }

    private fun initPresenter() {
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
    }

    private fun init(view: View) {
        tvTemp = view.findViewById(R.id.fragment_current_weather__tvTemp)
        tvFeelLike = view.findViewById(R.id.fragment_current_weather__tvFeelLike)
        tvPressure = view.findViewById(R.id.fragment_current_weather__tvPressure)
        tvHumidity = view.findViewById(R.id.fragment_current_weather__tvHumidity)
        tvWindSpeed = view.findViewById(R.id.fragment_current_weather__tvWindSpeed)
        tvDescription = view.findViewById(R.id.fragment_current_weather__tvDescription)
        ivDescription = view.findViewById(R.id.fragment_current_weather__ivDescription)
        progressBar = view.findViewById(R.id.fragment_current_weather__pbLoading)
    }

    private fun initData(currentWeatherView: CurrentWeatherView?) {
        tvTemp.text = currentWeatherView!!.temp
        tvFeelLike.text = currentWeatherView.feelLike
        tvPressure.text = currentWeatherView.pressure
        tvHumidity.text = currentWeatherView.humidity
        tvWindSpeed.text = currentWeatherView.windSpeed
        tvDescription.text = currentWeatherView.weatherDescription
        ivDescription.setImageResource(currentWeatherView.iconId)
    }
}