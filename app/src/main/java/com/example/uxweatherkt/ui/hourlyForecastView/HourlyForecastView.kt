package com.example.uxweatherkt.ui.hourlyForecastView

import android.location.Location
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastPresenter
import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.ui.baseView.BaseView
import com.example.uxweatherkt.ui.WeatherView

class HourlyForecastView : BaseView, WeatherView {

    constructor(
        baseRootView: View,
        lifecycleOwner: LifecycleOwner,
        hourlyForecastPresenter: HourlyForecastPresenter?,
        hourlyForecastAdapter: HourlyForecastAdapter,
        linearLayoutManager: LinearLayoutManager
    ) {
        this.baseRootView = baseRootView
        this.hourlyForecastPresenter = hourlyForecastPresenter
        this.lifecycleOwner = lifecycleOwner
        this.hourlyForecastAdapter = hourlyForecastAdapter
        this.linearLayoutManager = linearLayoutManager
        init()
    }

    private var lifecycleOwner: LifecycleOwner
    private var hourlyForecastPresenter: HourlyForecastPresenter?
    private var hourlyForecastAdapter: HourlyForecastAdapter
    private var linearLayoutManager: LinearLayoutManager

    private lateinit var liveData: MutableLiveData<List<HourForecastRow>>

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    override fun bindData() {
        hideLoading()
        initData(liveData.value as ArrayList)
    }

    override fun showLoading() {
        baseRootView.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

    fun onLocationReady(location: Location) {
        showLoading()
        hourlyForecastPresenter?.getData(location)
    }

    fun onCityNameReady(cityName: String) {
        showLoading()
        hourlyForecastPresenter?.getData(cityName)
    }

    private fun init() {
        liveData = hourlyForecastPresenter!!.getLiveData()
        liveData.observe(lifecycleOwner, Observer {bindData()})
        progress = baseRootView.findViewById(R.id.view_recycler__pbLoading)
        recyclerView = baseRootView.findViewById(R.id.view_recycler__recyclerView)
        recyclerView.adapter = hourlyForecastAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun initData(hourlyForecastRow: ArrayList<HourForecastRow>) {
        hourlyForecastAdapter.setDailyForecast(hourlyForecastRow)
    }
}