package com.example.uxweatherkt.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.DayForecastView

class DailyForecastListFragment : Fragment(), DailyForecastListAdapter.Listener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_forecast_list, container, false)
        val dailyForecast = ArrayList<DayForecastView>()
        val dailyForecastListAdapter = DailyForecastListAdapter(dailyForecast, this)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_daily_forecast_list_recyclerView)
        recyclerView.adapter = dailyForecastListAdapter
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        return view
    }

    fun initData(dailyForecastView: ArrayList<DayForecastView>) {

    }

    override fun onDayForecastClick() {
        TODO("Not yet implemented")
    }
}
