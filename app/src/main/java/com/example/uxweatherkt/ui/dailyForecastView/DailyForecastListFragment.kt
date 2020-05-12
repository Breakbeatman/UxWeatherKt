package com.example.uxweatherkt.ui.dailyForecastView

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.App

import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenter
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastPresenterImpl
import com.example.uxweatherkt.presenter.dailyForecast.DailyForecastViewModel
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.WeatherView

class DailyForecastListFragment : Fragment(), WeatherView,
    DailyForecastListAdapter.Listener {

    interface Listener {
        fun onDailyForecastListItemClicked()
    }

    private var dailyForecastPresenter: DailyForecastPresenter? = null

    private lateinit var listener: Listener
    private lateinit var dailyForecastView: ArrayList<DayForecastView>
    private lateinit var dailyForecastListAdapter: DailyForecastListAdapter
    private lateinit var liveData: MutableLiveData<List<DayForecastView>>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        liveData = dailyForecastPresenter!!.getLiveData()
        liveData.observe(this, Observer { showWeather() })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_forecast_list, container, false)
        init(view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        dailyForecastPresenter!!.detachView()
    }

    override fun showWeather() {
        hideLoading()
        if (liveData.value == null) {
            dataIsNotAvailable()
            return
        }
        initData(liveData.value as ArrayList)
    }

    override fun dataIsNotAvailable() {
        Toast.makeText(activity, "asdasd", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onDayForecastClick() {
        TODO("Not yet implemented")
    }

    fun onLocationReady(location: Location) {
        progressBar = activity!!.findViewById(R.id.fragment_daily_forecast_list__pbLoading)
        showLoading()
        dailyForecastPresenter!!.getData(location)
    }

    private fun initPresenter() {
        val viewModel = ViewModelProviders.of(this).get(DailyForecastViewModel::class.java)
        dailyForecastPresenter = viewModel.dailyForecastPresenter
        if (dailyForecastPresenter == null) {
            val weatherModel = (activity!!.application as App).getDependencyRoot().weatherModel
            val dailyForecastDataBinder =
                (activity!!.application as App).getDependencyRoot().dailyWeatherDataBinder
            dailyForecastPresenter =
                DailyForecastPresenterImpl(weatherModel, dailyForecastDataBinder)
        }
        dailyForecastPresenter!!.attachView(this)
    }

    private fun init(view: View) {
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
    }

    private fun initData(dailyForecastView: ArrayList<DayForecastView>) {
        this.dailyForecastView = dailyForecastView
        dailyForecastListAdapter.setDailyForecast(dailyForecastView)
    }
}
