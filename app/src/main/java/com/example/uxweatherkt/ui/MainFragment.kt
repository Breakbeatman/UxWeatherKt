package com.example.uxweatherkt.ui

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.App

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenterImpl
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherViewModel
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastPresenter
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastPresenterImpl
import com.example.uxweatherkt.presenter.dailyForecastPresenter.DailyForecastViewModel
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastPresenter
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastPresenterImpl
import com.example.uxweatherkt.presenter.hourlyForecastPresenter.HourlyForecastViewModel
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.currentWeatherView.CurrentWeatherView
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastListAdapter
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastView
import com.example.uxweatherkt.ui.hourlyForecastView.HourlyForecastView

class MainFragment : Fragment(), DailyForecastListAdapter.Listener {

    private lateinit var currentWeatherView: CurrentWeatherView
    private lateinit var hourlyForecastView: HourlyForecastView
    private lateinit var dailyForecastView: DailyForecastView

    private var currentWeatherPresenter: CurrentWeatherPresenter? = null
    private var hourlyForecastPresenter: HourlyForecastPresenter? = null
    private var dailyForecastPresenter: DailyForecastPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCurrentWeatherPresenter()
        initHourlyForecastPresenter()
        initDailyForecastPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initViews(view)
        currentWeatherPresenter?.attachView(currentWeatherView)
        hourlyForecastPresenter?.attachView(hourlyForecastView)
        dailyForecastPresenter?.attachView(dailyForecastView)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        currentWeatherPresenter?.detachView()
        hourlyForecastPresenter?.detachView()
        dailyForecastPresenter?.detachView()
    }

    fun passLocation(location: Location) {
        currentWeatherView.onLocationReady(location)
        dailyForecastView.onLocationReady(location)
    }

    fun passCityName(cityName: String) {
        currentWeatherView.onCityNameReady(cityName)
        dailyForecastView.onCityNameReady(cityName)
    }

    private fun initCurrentWeatherPresenter() {
        val viewModel: CurrentWeatherViewModel =
            ViewModelProviders.of(this)
                .get(CurrentWeatherViewModel::class.java)
        currentWeatherPresenter = viewModel.currentWeatherPresenter
        if (currentWeatherPresenter == null) {
            val weatherModel =
                (activity!!.application as App).getDependencyRoot().weatherModel
            val currentWeatherDataBinder =
                (activity!!.application as App).getDependencyRoot().currentWeatherDataBinder
            currentWeatherPresenter =
                CurrentWeatherPresenterImpl(weatherModel, currentWeatherDataBinder)
        }

    }

    private fun initHourlyForecastPresenter() {
        val viewModel = ViewModelProviders.of(this).get(HourlyForecastViewModel::class.java)
        hourlyForecastPresenter = viewModel.hourlyForecastPresenter
        if (hourlyForecastPresenter == null) {
            val weatherModel =
                (activity!!.application as App).getDependencyRoot().weatherModel
            val hourlyForecastDataBinder =
                (activity!!.application as App).getDependencyRoot().hourlyForecastDataBinder
            hourlyForecastPresenter =
                HourlyForecastPresenterImpl(weatherModel, hourlyForecastDataBinder)
        }
    }

    private fun initDailyForecastPresenter() {
        val viewModel = ViewModelProviders.of(this).get(DailyForecastViewModel::class.java)
        dailyForecastPresenter = viewModel.dailyForecastPresenter
        if (dailyForecastPresenter == null) {
            val weatherModel = (activity!!.application as App).getDependencyRoot().weatherModel
            val dailyForecastDataBinder =
                (activity!!.application as App).getDependencyRoot().dailyWeatherDataBinder
            dailyForecastPresenter =
                DailyForecastPresenterImpl(weatherModel, dailyForecastDataBinder)
        }
    }

    private fun initViews(view: View) {
        currentWeatherView = CurrentWeatherView(
            view.findViewById(R.id.fragment_main__view_current_weather), this,
            currentWeatherPresenter
        )
        hourlyForecastView = HourlyForecastView(
            view.findViewById(R.id.fragment_main__view_hourly_forecast),
            hourlyForecastPresenter
        )
        val dailyForecastListAdapter = DailyForecastListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        dailyForecastView = DailyForecastView(
            view.findViewById(R.id.fragment_main__view_daily_forecast), this,
            dailyForecastPresenter, dailyForecastListAdapter, linearLayoutManager
        )
    }

    override fun onDayForecastClick(dayForecastView: DayForecastView) {
        DetailDayActivity.StartObj.start(context ?: return, dayForecastView)
    }
}