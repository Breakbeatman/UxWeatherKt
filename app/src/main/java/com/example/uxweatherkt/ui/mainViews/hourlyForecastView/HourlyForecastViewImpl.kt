package com.example.uxweatherkt.ui.mainViews.hourlyForecastView

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastPresenter
import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.ui.baseView.BaseView

class HourlyForecastViewImpl : BaseView, HourlyForecastView, HourlyForecastAdapter.Listener {

    constructor(
        baseRootView: View,
        hourlyForecastPresenter: HourlyForecastPresenter?,
        listener: HourlyForecastView.Listener
    ) {
        this.baseRootView = baseRootView
        this.hourlyForecastPresenter = hourlyForecastPresenter
        this.listener = listener
        init()
    }

    private var hourlyForecastPresenter: HourlyForecastPresenter?
    private var listener: HourlyForecastView.Listener
    private lateinit var hourlyForecastAdapter: HourlyForecastAdapter

    private lateinit var tvTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    private fun init() {
        hourlyForecastAdapter = HourlyForecastAdapter(this)
        val linearLayoutManager =
            LinearLayoutManager(baseRootView.context, RecyclerView.HORIZONTAL, false)
        progress = baseRootView.findViewById(R.id.view_recycler__pbLoading)
        tvTitle = baseRootView.findViewById(R.id.view_recycler__tvTitle)
        tvTitle.text = baseRootView.resources.getString(R.string.hourly)
        recyclerView = baseRootView.findViewById(R.id.view_recycler__recyclerView)
        recyclerView.adapter = hourlyForecastAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun bindData(hourlyForecastRow: List<HourForecastRow>?) {
        hideLoading()
        if (hourlyForecastRow == null) {
            return
        }
        initData(hourlyForecastRow as ArrayList)
    }

    private fun initData(hourlyForecastRow: ArrayList<HourForecastRow>) {
        hourlyForecastAdapter.setDailyForecast(hourlyForecastRow)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
//        baseRootView.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

    override fun onHourForecastClick(hourForecastRow: HourForecastRow) {
        listener.onHourlyAdapterItemClick(hourForecastRow)
    }
}