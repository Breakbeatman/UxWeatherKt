package com.example.uxweatherkt.ui.currentWeatherView

import android.location.Location
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.currentWeatherPresenter.CurrentWeatherPresenter
import com.example.uxweatherkt.presenter.row.CurrentWeatherRow
import com.example.uxweatherkt.ui.baseView.BaseView
import com.example.uxweatherkt.ui.WeatherView

class CurrentWeatherView : BaseView, WeatherView {

    constructor(
        baseRootView: View,
        lifecycleOwner: LifecycleOwner,
        currentWeatherPresenter: CurrentWeatherPresenter?
    ) {
        this.baseRootView = baseRootView
        this.currentWeatherPresenter = currentWeatherPresenter
        this.lifecycleOwner = lifecycleOwner
        init()
    }

    private var lifecycleOwner: LifecycleOwner
    private var currentWeatherPresenter: CurrentWeatherPresenter?

    private lateinit var liveData: MutableLiveData<CurrentWeatherRow>

    private lateinit var tvDataIsNotAvailable: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvWindDir: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDescription: ImageView
    private lateinit var progress: ProgressBar
    private lateinit var tvDetail: TextView

    private lateinit var feelsLikeCardView: CardView
    private lateinit var ivFeelLike: ImageView
    private lateinit var tvFeelLikeTitle: TextView
    private lateinit var tvFeelLikeValue: TextView

    private lateinit var humidityCardView: CardView
    private lateinit var ivHumidity: ImageView
    private lateinit var tvHumidityTitle: TextView
    private lateinit var tvHumidityValue: TextView

    private lateinit var uvIndexCardView: CardView
    private lateinit var ivUvIndex: ImageView
    private lateinit var tvUvIndexTitle: TextView
    private lateinit var tvUvIndexValue: TextView

    private lateinit var visibilityCardView: CardView
    private lateinit var ivVisibility: ImageView
    private lateinit var tvVisibilityTitle: TextView
    private lateinit var tvVisibilityValue: TextView

    private lateinit var dewPointCardView: CardView
    private lateinit var ivDewPoint: ImageView
    private lateinit var tvDewPointTitle: TextView
    private lateinit var tvDewPointValue: TextView

    private lateinit var pressureCardView: CardView
    private lateinit var ivPressure: ImageView
    private lateinit var tvPressureTitle: TextView
    private lateinit var tvPressureValue: TextView

    override fun bindData() {
        hideLoading()
        if (liveData.value == null) {
            dataIsNotAvailable()
            return
        }
        initData(liveData.value)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
//        baseRootView.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
        baseRootView.visibility = View.VISIBLE
    }

    fun onLocationReady(location: Location) {
        showLoading()
        currentWeatherPresenter!!.getData(location)
    }

    fun onCityNameReady(cityName: String) {
        showLoading()
        currentWeatherPresenter!!.getData(cityName)
    }

    private fun init() {
        liveData = currentWeatherPresenter!!.getLiveData()
        liveData.observe(lifecycleOwner, Observer { bindData() })
        tvDate = baseRootView.findViewById(R.id.fragment_main__tvDate)
        tvTemp = baseRootView.findViewById(R.id.fragment_main__tvTemp)
        tvDetail = baseRootView.findViewById(R.id.fragment_main__tvDetail)
        tvWind = baseRootView.findViewById(R.id.fragment_main__tvWind)
        tvWindSpeed = baseRootView.findViewById(R.id.fragment_main__tvWindSpeed)
        tvWindDir = baseRootView.findViewById(R.id.fragment_main__tvWindDirection)
        tvDescription = baseRootView.findViewById(R.id.fragment_main__tvDescription)
        ivDescription = baseRootView.findViewById(R.id.fragment_main__ivDescription)
        tvDataIsNotAvailable =
            baseRootView.findViewById((R.id.fragment_main__tvDataIsNotAvailable))
        progress = baseRootView.findViewById(R.id.fragment_main__pbLoading)

        feelsLikeCardView = baseRootView.findViewById(R.id.fragment_main__cvFeelsLike)
        ivFeelLike =
            feelsLikeCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvFeelLikeTitle =
            feelsLikeCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvFeelLikeValue =
            feelsLikeCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)

        humidityCardView = baseRootView.findViewById(R.id.fragment_main__cvHumidity)
        ivHumidity =
            humidityCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvHumidityTitle =
            humidityCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvHumidityValue =
            humidityCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)

        uvIndexCardView = baseRootView.findViewById(R.id.fragment_main__cvUvIndex)
        ivUvIndex = uvIndexCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvUvIndexTitle =
            uvIndexCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvUvIndexValue =
            uvIndexCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)

        visibilityCardView = baseRootView.findViewById(R.id.fragment_main__cvVisibility)
        ivVisibility =
            visibilityCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvVisibilityTitle =
            visibilityCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvVisibilityValue =
            visibilityCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)

        dewPointCardView = baseRootView.findViewById(R.id.fragment_main__cvDewPoint)
        ivDewPoint =
            dewPointCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvDewPointTitle =
            dewPointCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvDewPointValue =
            dewPointCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)

        pressureCardView = baseRootView.findViewById(R.id.fragment_main__cvPressure)
        ivPressure =
            pressureCardView.findViewById(R.id.card_detail_current_weather__ivParameterType)
        tvPressureTitle =
            pressureCardView.findViewById(R.id.card_detail_current_weather__tvParameterTitle)
        tvPressureValue =
            pressureCardView.findViewById(R.id.card_detail_current_weather__tvParameterValue)
    }

    private fun initData(currentWeatherRow: CurrentWeatherRow?) {
        tvDate.text = currentWeatherRow?.date
        tvTemp.text = currentWeatherRow?.temp
        tvDescription.text = currentWeatherRow?.weatherDescription
        ivDescription.setImageResource(currentWeatherRow!!.iconId)
        tvDetail.visibility = View.VISIBLE
        tvWindSpeed.text = currentWeatherRow.windSpeed
        tvWindDir.text = currentWeatherRow.windDir
        tvWind.visibility = View.VISIBLE
        initDataFeelLikeCardView(currentWeatherRow)
        initDataHumidityCardView(currentWeatherRow)
        initDataUvIndexCardView(currentWeatherRow)
        initDataVisibilityCardView(currentWeatherRow)
        initDataDewPointCardView(currentWeatherRow)
        initDataPressureCardView(currentWeatherRow)
    }

    private fun dataIsNotAvailable() {
        tvDataIsNotAvailable.visibility = View.VISIBLE
    }

    private fun initDataFeelLikeCardView(currentWeatherRow: CurrentWeatherRow?) {
        feelsLikeCardView.visibility = View.VISIBLE
        ivFeelLike.setImageResource(R.drawable.ic_feels_like)
        tvFeelLikeTitle.text = baseRootView.resources.getString(R.string.feels_like)
        tvFeelLikeValue.text = currentWeatherRow?.feelLike
    }

    private fun initDataHumidityCardView(currentWeatherRow: CurrentWeatherRow?) {
        humidityCardView.visibility = View.VISIBLE
        ivHumidity.setImageResource(R.drawable.ic_humidity)
        tvHumidityTitle.text = baseRootView.resources.getString(R.string.humidity)
        tvHumidityValue.text = currentWeatherRow?.humidity
    }

    private fun initDataUvIndexCardView(currentWeatherRow: CurrentWeatherRow?) {
        uvIndexCardView.visibility = View.VISIBLE
        ivUvIndex.setImageResource(R.drawable.ic_uv_index)
        tvUvIndexTitle.text = baseRootView.resources.getString(R.string.uv_index)
        tvUvIndexValue.text = currentWeatherRow?.uvIndex
    }

    private fun initDataVisibilityCardView(currentWeatherRow: CurrentWeatherRow?) {
        visibilityCardView.visibility = View.VISIBLE
        ivVisibility.setImageResource(R.drawable.ic_visibility)
        tvVisibilityTitle.text = baseRootView.resources.getString(R.string.visibility)
        tvVisibilityValue.text = currentWeatherRow?.visibility
    }

    private fun initDataDewPointCardView(currentWeatherRow: CurrentWeatherRow?) {
        dewPointCardView.visibility = View.VISIBLE
        ivDewPoint.setImageResource(R.drawable.ic_dew_point)
        tvDewPointTitle.text = baseRootView.resources.getString(R.string.dew_point)
        tvDewPointValue.text = currentWeatherRow?.dewPoint
    }

    private fun initDataPressureCardView(currentWeatherRow: CurrentWeatherRow?) {
        pressureCardView.visibility = View.VISIBLE
        ivPressure.setImageResource(R.drawable.ic_pressure)
        tvPressureTitle.text = baseRootView.resources.getString(R.string.pressure)
        tvPressureValue.text = currentWeatherRow?.pressure
    }
}