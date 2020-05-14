package com.example.uxweatherkt.ui.hourlyForecastView

import android.view.View
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastPresenter
import com.example.uxweatherkt.ui.BaseView
import com.example.uxweatherkt.ui.WeatherView

class HourlyForecastView: BaseView, WeatherView {

    constructor(baseRootView: View, hourlyForecastPresenter: HourlyForecastPresenter?) {
        this.baseRootView = baseRootView
//        TODO init
    }

    override fun bindData() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }
}