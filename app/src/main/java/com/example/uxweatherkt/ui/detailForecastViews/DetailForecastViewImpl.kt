package com.example.uxweatherkt.ui.detailForecastViews

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.ui.baseView.BaseView

class DetailForecastViewImpl: BaseView, DetailForecastView {

    constructor(
        baseRootView: View
    ) {
        this.baseRootView = baseRootView
        init()
    }

    private lateinit var detailForecastAdapter: DetailForecastAdapter

    private lateinit var recyclerView: RecyclerView

    override fun bindData(dailyForecastRow: List<DayForecastRow>?) {
        hideLoading()
        if (dailyForecastRow == null) {
            return
        }
        initData(dailyForecastRow)
    }

    private fun initData(dailyForecastRow: List<DayForecastRow>) {
        detailForecastAdapter.setDetailDailyForecast(dailyForecastRow)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun init() {
        detailForecastAdapter = DetailForecastAdapter()
        val linearLayoutManager = LinearLayoutManager(baseRootView.context, RecyclerView.VERTICAL, false)
        recyclerView = baseRootView.findViewById(R.id.fragment_detail__rvDetailWeather)
        recyclerView.adapter = detailForecastAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

}