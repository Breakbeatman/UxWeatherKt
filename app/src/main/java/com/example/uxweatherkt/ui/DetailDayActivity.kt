package com.example.uxweatherkt.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uxweatherkt.DAY_FORECAST_VIEW_KEY
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.DayForecastView
import com.example.uxweatherkt.ui.dailyForecastView.DailyForecastView


class DetailDayActivity : AppCompatActivity() {

    object StartObj {
        fun start(context: Context, dayForecastView: DayForecastView) {
            val intent = Intent(context, DetailDayActivity::class.java)
            intent.putExtra(DAY_FORECAST_VIEW_KEY, dayForecastView)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_day)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.activity_detail_day__container, BlankFragment(), "fr1").commit()
//        var dayForecastView = DailyForecastView(layoutInflater, findViewById(R.id.activity_detail_day__ll))
    }
}
