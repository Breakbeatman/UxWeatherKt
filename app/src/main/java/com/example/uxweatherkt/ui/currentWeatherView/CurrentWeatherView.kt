package com.example.uxweatherkt.ui.currentWeatherView

import android.location.Location
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherView
import com.example.uxweatherkt.ui.BaseView
import com.example.uxweatherkt.ui.WeatherView

class CurrentWeatherView : BaseView, WeatherView {

    constructor(
        baseRootView: View,
        lifecycleOwner: LifecycleOwner,
        currentWeatherPresenter: CurrentWeatherPresenter?
    ) {
        this.baseRootView = baseRootView
        this.currentWeatherPresenter = currentWeatherPresenter
        this.lifecycleOwner = lifecycleOwner
        init(baseRootView)
    }

    private var lifecycleOwner: LifecycleOwner
    private var currentWeatherPresenter: CurrentWeatherPresenter?

    private lateinit var liveData: MutableLiveData<CurrentWeatherView>

    private lateinit var tvDataIsNotAvailable: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvFeelLike: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDescription: ImageView
    private lateinit var progress: ProgressBar

    override fun bindData() {
        hideLoading()
        if (liveData.value == null) {
            dataIsNotAvailable()
            return
        }
        initData(liveData.value)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
        baseRootView.visibility = View.GONE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

    fun onLocationReady(location: Location) {
        showLoading()
        currentWeatherPresenter!!.getData(location)
    }

    fun onCityNameReady(cityName: String) {
        showLoading()
        currentWeatherPresenter!!.getData(cityName)
    }

    private fun init(view: View) {
        liveData = currentWeatherPresenter!!.getLiveData()
        liveData.observe(lifecycleOwner, Observer { bindData() })
        tvTemp = view.findViewById(R.id.fragment_main__tvTemp)
        tvFeelLike = view.findViewById(R.id.fragment_main__tvFeelLike)
        tvPressure = view.findViewById(R.id.fragment_main__tvPressure)
        tvHumidity = view.findViewById(R.id.fragment_main__tvHumidity)
        tvWindSpeed = view.findViewById(R.id.fragment_main__tvWindSpeed)
        tvDescription = view.findViewById(R.id.fragment_main__tvDescription)
        ivDescription = view.findViewById(R.id.fragment_main__ivDescription)
        progress = view.findViewById(R.id.fragment_main__pbLoading)
    }

    private fun initData(currentWeatherView: CurrentWeatherView?) {
        tvTemp.text = currentWeatherView?.temp
        tvFeelLike.text = currentWeatherView?.feelLike
        tvPressure.text = currentWeatherView?.pressure
        tvHumidity.text = currentWeatherView?.humidity
        tvWindSpeed.text = currentWeatherView?.windSpeed
        tvDescription.text = currentWeatherView?.weatherDescription
        ivDescription.setImageResource(currentWeatherView!!.iconId)
    }

    private fun dataIsNotAvailable() {
        val stringing = "DATA IS NOT AVAILABLE"
        tvDataIsNotAvailable =
            baseRootView.findViewById((R.id.fragment_main__tvDataIsNotAvailable))
        tvDataIsNotAvailable.text = stringing
    }
}