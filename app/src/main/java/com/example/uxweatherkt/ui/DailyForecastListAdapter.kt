package com.example.uxweatherkt.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.DayForecastView

class DailyForecastListAdapter(
    var dailyForecast: ArrayList<DayForecastView>,
    var listener: Listener
) :
    RecyclerView.Adapter<DailyForecastListAdapter.ViewHolder>() {

    interface Listener {
        fun onDayForecastClick()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_one_day_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        var oneDayForecastView = dailyForecast[position]
        holder.tv1.text = "YO"
        holder.tv2.text = "YO"
        holder.tv3.text = "YO"
    }

    override fun getItemCount(): Int {
//        return dailyForecast.size
        return 10
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv1: TextView
        val tv2: TextView
        val tv3: TextView

        init {
            tv1 = itemView.findViewById(R.id.tv1)
            tv2 = itemView.findViewById(R.id.tv2)
            tv3 = itemView.findViewById(R.id.tv3)
        }
    }
}