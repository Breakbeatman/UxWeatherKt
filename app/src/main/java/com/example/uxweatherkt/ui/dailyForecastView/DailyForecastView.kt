package com.example.uxweatherkt.ui.dailyForecastView

import android.location.Location
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastPresenter
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.BaseView
import com.example.uxweatherkt.ui.WeatherView

class DailyForecastView : BaseView, WeatherView {

    constructor(
        baseRootView: View,
        lifecycleOwner: LifecycleOwner,
        dailyForecastPresenter: DailyForecastPresenter?,
        dailyForecastListAdapter: DailyForecastListAdapter,
        linearLayoutManager: LinearLayoutManager
    ) {
        this.baseRootView = baseRootView
        this.lifecycleOwner = lifecycleOwner
        this.dailyForecastPresenter = dailyForecastPresenter
        this.dailyForecastListAdapter = dailyForecastListAdapter
        this.linearLayoutManager = linearLayoutManager
        init()
    }

    private var lifecycleOwner: LifecycleOwner
    private var dailyForecastPresenter: DailyForecastPresenter? = null
    private var dailyForecastListAdapter: DailyForecastListAdapter
    private var linearLayoutManager: LinearLayoutManager

    private lateinit var liveData: MutableLiveData<List<DayForecastView>>

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar


    override fun bindData() {
        hideLoading()
        initData(liveData.value as ArrayList)
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
        dailyForecastPresenter!!.getData(location)
    }

    fun onCityNameReady(cityName: String) {
        showLoading()
        dailyForecastPresenter!!.getData(cityName)
    }

    private fun init() {
        liveData = dailyForecastPresenter!!.getLiveData()
        liveData.observe(lifecycleOwner, Observer { bindData() })
        progress = findViewById(R.id.view_daily_forecast__pbLoading) as ProgressBar
        recyclerView = findViewById(R.id.view_daily_forecast__recyclerView) as RecyclerView
        recyclerView.adapter = dailyForecastListAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun initData(dailyForecastView: ArrayList<DayForecastView>) {
        dailyForecastListAdapter.setDailyForecast(dailyForecastView)
    }
}