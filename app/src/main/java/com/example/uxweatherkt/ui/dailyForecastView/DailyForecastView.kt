package com.example.uxweatherkt.ui.dailyForecastView

import android.location.Location
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastPresenter
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.ui.baseView.BaseView
import com.example.uxweatherkt.ui.WeatherView

class DailyForecastView : BaseView, WeatherView {

    constructor(
        baseRootView: View,
        lifecycleOwner: LifecycleOwner,
        dailyForecastPresenter: DailyForecastPresenter?,
        dailyForecastAdapter: DailyForecastAdapter,
        linearLayoutManager: LinearLayoutManager
    ) {
        this.baseRootView = baseRootView
        this.lifecycleOwner = lifecycleOwner
        this.dailyForecastPresenter = dailyForecastPresenter
        this.dailyForecastAdapter = dailyForecastAdapter
        this.linearLayoutManager = linearLayoutManager
        init()
    }

    private var lifecycleOwner: LifecycleOwner
    private var dailyForecastPresenter: DailyForecastPresenter? = null
    private var dailyForecastAdapter: DailyForecastAdapter
    private var linearLayoutManager: LinearLayoutManager

    private lateinit var liveData: MutableLiveData<List<DayForecastRow>>

    private lateinit var tvTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    override fun bindData() {
        hideLoading()
        if (liveData.value == null) {
            return
        }
        initData(liveData.value as ArrayList)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
//        baseRootView.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

//    fun onLocationReady(location: Location) {
//        showLoading()
//        dailyForecastPresenter?.getWeatherData(location)
//    }
//
//    fun onCityNameReady(cityName: String) {
//        showLoading()
//        dailyForecastPresenter?.getWeatherData(cityName)
//    }

    private fun init() {
        liveData = dailyForecastPresenter!!.getLiveData()
        liveData.observe(lifecycleOwner, Observer { bindData() })
        progress = baseRootView.findViewById(R.id.view_recycler__pbLoading)
        tvTitle = baseRootView.findViewById(R.id.view_recycler__tvTitle)
        tvTitle.text = baseRootView.resources.getString(R.string.daily)
        recyclerView = baseRootView.findViewById(R.id.view_recycler__recyclerView)
        recyclerView.adapter = dailyForecastAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun initData(dailyForecastRow: ArrayList<DayForecastRow>) {
        dailyForecastAdapter.setDailyForecast(dailyForecastRow)
    }
}