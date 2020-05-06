package com.example.uxweatherkt.ui.dailyForecastView


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.App

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.RetainedFragment
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenter
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.WeatherView

class DailyForecastListFragment : Fragment(), WeatherView,
    DailyForecastListAdapter.Listener {

    interface Listener {
        fun onDailyForecastListItemClicked()
    }

    private lateinit var dailyForecastPresenter: DailyForecastPresenter

    private lateinit var listener: Listener
    private lateinit var dailyForecastView: ArrayList<DayForecastView>
    private lateinit var dailyForecastListAdapter: DailyForecastListAdapter
    private lateinit var liveData: MutableLiveData<List<DayForecastView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dailyForecastPresenter =
            (activity!!.application as App).getDependencyRoot().dailyForecastPresenter
        dailyForecastPresenter.attachView(this)
        liveData = dailyForecastPresenter.getLiveData()
        liveData.observe(this, Observer { showWeather() })
        dailyForecastPresenter.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_forecast_list, container, false)
        val dailyForecastView = ArrayList<DayForecastView>()
        dailyForecastListAdapter =
            DailyForecastListAdapter(
                dailyForecastView,
                this
            )
        val recyclerView: RecyclerView =
            view.findViewById(R.id.fragment_daily_forecast_list__recyclerView)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = dailyForecastListAdapter
        recyclerView.layoutManager = linearLayoutManager
        return view
    }

    override fun showWeather() {
        val dailyForecastView = liveData.value as ArrayList
        initData(dailyForecastView)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onDayForecastClick() {
        TODO("Not yet implemented")
    }

    private fun initData(dailyForecastView: ArrayList<DayForecastView>) {
        this.dailyForecastView = dailyForecastView
        dailyForecastListAdapter.setDailyForecast(dailyForecastView)
    }
}
