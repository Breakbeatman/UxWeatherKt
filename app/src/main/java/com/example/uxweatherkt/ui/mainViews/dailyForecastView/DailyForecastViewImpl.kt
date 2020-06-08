package com.example.uxweatherkt.ui.mainViews.dailyForecastView

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

class DailyForecastViewImpl : BaseView, DailyForecastView, DailyForecastAdapter.Listener {

    constructor(
        baseRootView: View,
        dailyForecastPresenter: DailyForecastPresenter?,
        listener: DailyForecastView.Listener
    ) {
        this.baseRootView = baseRootView
        this.dailyForecastPresenter = dailyForecastPresenter
        this.listener = listener
        init()
    }

    private var dailyForecastPresenter: DailyForecastPresenter? = null
    private var listener: DailyForecastView.Listener
    private lateinit var dailyForecastAdapter: DailyForecastAdapter

    private lateinit var tvTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    override fun bindData(dailyForecastRow: List<DayForecastRow>?) {
        hideLoading()
        if (dailyForecastRow == null) {
            return
        }
        initData(dailyForecastRow as ArrayList)
    }

    private fun initData(dailyForecastRow: List<DayForecastRow>) {
        dailyForecastAdapter.setDailyForecast(dailyForecastRow)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
//        baseRootView.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

    private fun init() {
        dailyForecastAdapter = DailyForecastAdapter(this)
        val linearLayoutManager =
            LinearLayoutManager(baseRootView.context, RecyclerView.HORIZONTAL, false)
        progress = baseRootView.findViewById(R.id.view_recycler__pbLoading)
        tvTitle = baseRootView.findViewById(R.id.view_recycler__tvTitle)
        tvTitle.text = baseRootView.resources.getString(R.string.daily)
        recyclerView = baseRootView.findViewById(R.id.view_recycler__recyclerView)
        recyclerView.adapter = dailyForecastAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onDayForecastClick(dayForecastRow: DayForecastRow) {
        listener.onDailyAdapterItemClick(dayForecastRow)
    }
}