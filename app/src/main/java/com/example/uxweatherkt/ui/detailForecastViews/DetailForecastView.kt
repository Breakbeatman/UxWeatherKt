package com.example.uxweatherkt.ui.detailForecastViews

import android.view.View
import com.example.uxweatherkt.ui.WeatherView
import com.example.uxweatherkt.ui.baseView.BaseView

class DetailForecastView: BaseView, WeatherView {

    constructor(
        baseRootView: View
    ) {
        this.baseRootView = baseRootView
        init()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun init() {

    }

}