package com.example.uxweatherkt.ui


import android.content.Context
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

    interface Listener {
        fun onDailyForecastListItemClicked()
    }

    private lateinit var listener: Listener
    private lateinit var dailyForecastView: ArrayList<DayForecastView>
    private lateinit var dailyForecastListAdapter: DailyForecastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_forecast_list, container, false)
        val dailyForecastView = ArrayList<DayForecastView>()
        dailyForecastListAdapter = DailyForecastListAdapter(dailyForecastView, this)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_daily_forecast_list__recyclerView)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = dailyForecastListAdapter
        recyclerView.layoutManager = linearLayoutManager
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.listener = context as Listener
    }

    fun initData(dailyForecastView: ArrayList<DayForecastView>) {
        this.dailyForecastView = dailyForecastView
        dailyForecastListAdapter.setDailyForecast(dailyForecastView)
    }

    override fun onDayForecastClick() {
        TODO("Not yet implemented")
    }
}
