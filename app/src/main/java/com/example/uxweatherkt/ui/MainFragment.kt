package com.example.uxweatherkt.ui

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.presenter.row.DayForecastRow
import com.example.uxweatherkt.presenter.row.HourForecastRow
import com.example.uxweatherkt.ui.mainViews.currentWeatherView.CurrentWeatherView
import com.example.uxweatherkt.ui.mainViews.currentWeatherView.CurrentWeatherViewImpl
import com.example.uxweatherkt.ui.mainViews.dailyForecastView.DailyForecastAdapter
import com.example.uxweatherkt.ui.mainViews.dailyForecastView.DailyForecastView
import com.example.uxweatherkt.ui.mainViews.dailyForecastView.DailyForecastViewImpl
import com.example.uxweatherkt.ui.mainViews.hourlyForecastView.HourlyForecastAdapter
import com.example.uxweatherkt.ui.mainViews.hourlyForecastView.HourlyForecastView
import com.example.uxweatherkt.ui.mainViews.hourlyForecastView.HourlyForecastViewImpl

class MainFragment : Fragment(), HourlyForecastView.Listener, DailyForecastView.Listener {

    private lateinit var currentWeatherView: CurrentWeatherView
    private lateinit var hourlyForecastView: HourlyForecastView
    private lateinit var dailyForecastView: DailyForecastView

    private var currentWeatherPresenter: CurrentWeatherPresenter? = null
    private var hourlyForecastPresenter: HourlyForecastPresenter? = null
    private var dailyForecastPresenter: DailyForecastPresenter? = null

    private lateinit var currentLiveData: MutableLiveData<CurrentWeatherRow>
    private lateinit var hourlyLiveData: MutableLiveData<List<HourForecastRow>>
    private lateinit var dailyLiveData: MutableLiveData<List<DayForecastRow>>

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
        observePresentersLiveData()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        currentWeatherPresenter?.detachView()
        hourlyForecastPresenter?.detachView()
        dailyForecastPresenter?.detachView()
    }

    fun passLocation(location: Location) {
        currentWeatherPresenter?.getWeatherData(location)
        hourlyForecastPresenter?.getWeatherData(location)
        dailyForecastPresenter?.getWeatherData(location)
    }

    fun passCityName(cityName: String) {
        currentWeatherPresenter?.getWeatherData(cityName)
        hourlyForecastPresenter?.getWeatherData(cityName)
        dailyForecastPresenter?.getWeatherData(cityName)
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
        currentWeatherView = CurrentWeatherViewImpl(
            view.findViewById(R.id.fragment_main__view_current_weather),
            currentWeatherPresenter
        )
        hourlyForecastView = HourlyForecastViewImpl(
            view.findViewById(R.id.fragment_main__view_hourly_forecast),
            hourlyForecastPresenter, this
        )
        dailyForecastView = DailyForecastViewImpl(
            view.findViewById(R.id.fragment_main__view_daily_forecast),
            dailyForecastPresenter, this
        )
    }

    private fun observePresentersLiveData() {
        currentLiveData = currentWeatherPresenter!!.getLiveData()
        currentLiveData.observe(
            this,
            Observer { currentWeatherRow -> currentWeatherView.bindData(currentWeatherRow) })
        hourlyLiveData = hourlyForecastPresenter!!.getLiveData()
        hourlyLiveData.observe(
            this,
            Observer { hourlyForecastRow -> hourlyForecastView.bindData(hourlyForecastRow) })
        dailyLiveData = dailyForecastPresenter!!.getLiveData()
        dailyLiveData.observe(
            this,
            Observer { dailyForecastRow -> dailyForecastView.bindData(dailyForecastRow) })
    }

    override fun onHourlyAdapterItemClick(hourForecastRow: HourForecastRow) {
        TODO("Not yet implemented")
    }

    override fun onDailyAdapterItemClick(dayForecastRow: DayForecastRow) {
        DetailDayActivity.StartObj.start(context ?: return, dayForecastRow)
    }
}