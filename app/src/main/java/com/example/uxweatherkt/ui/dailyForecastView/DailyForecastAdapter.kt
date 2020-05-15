package com.example.uxweatherkt.ui.dailyForecastView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.DayForecastRow

class DailyForecastAdapter(var listener: Listener) :
    RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {

    interface Listener {
        fun onDayForecastClick(dayForecastRow: DayForecastRow)
    }

    private var dailyForecastView = ArrayList<DayForecastRow>()

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_day_forecast, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dayForecastView = dailyForecastView[position]
        holder.tvDate.text = dayForecastView.date
        holder.tvDayOfWeek.text = "today"
        holder.tvMaxTemp.text = dayForecastView.maxTemp
        holder.tvMinTemp.text = dayForecastView.minTemp
        holder.ivWeather.setImageResource(dayForecastView.iconId)
        holder.itemView.setOnClickListener { listener.onDayForecastClick(dayForecastView) }
    }

    override fun getItemCount(): Int {
        return dailyForecastView.size
    }

    fun setDailyForecast(dailyForecastRow: ArrayList<DayForecastRow>) {
        this.dailyForecastView.clear()
        this.dailyForecastView.addAll(dailyForecastRow)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView
        val tvDayOfWeek: TextView
        val tvMaxTemp: TextView
        val tvMinTemp: TextView
        val ivWeather: ImageView

        init {
            tvDate = itemView.findViewById(R.id.row_day_forecast__tvDate)
            tvDayOfWeek = itemView.findViewById(R.id.row_day_forecast__tvDayOfWeek)
            tvMaxTemp = itemView.findViewById(R.id.row_day_forecast__tvTempMax)
            tvMinTemp = itemView.findViewById(R.id.row_day_forecast__tvTempMin)
            ivWeather = itemView.findViewById(R.id.row_day_forecast__ivWeather)
        }
    }
}