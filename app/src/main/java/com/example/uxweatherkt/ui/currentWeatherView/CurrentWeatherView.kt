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
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.ui.baseView.BaseView
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

    private lateinit var liveData: MutableLiveData<CurrentWeatherRow>

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
        tvDataIsNotAvailable =
            baseRootView.findViewById((R.id.fragment_main__tvDataIsNotAvailable))
        progress = view.findViewById(R.id.fragment_main__pbLoading)
    }

    private fun initData(currentWeatherRow: CurrentWeatherRow?) {
        tvTemp.text = currentWeatherRow?.temp
        tvFeelLike.text = currentWeatherRow?.feelLike
        tvPressure.text = currentWeatherRow?.pressure
        tvHumidity.text = currentWeatherRow?.humidity
        tvWindSpeed.text = currentWeatherRow?.windSpeed
        tvDescription.text = currentWeatherRow?.weatherDescription
        ivDescription.setImageResource(currentWeatherRow!!.iconId)
    }

    private fun dataIsNotAvailable() {
        tvDataIsNotAvailable.visibility = View.VISIBLE
    }
}