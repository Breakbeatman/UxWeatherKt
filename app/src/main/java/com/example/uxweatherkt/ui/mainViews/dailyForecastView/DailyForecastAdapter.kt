package com.example.uxweatherkt.ui.mainViews.dailyForecastView

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

    private var dailyForecastRows = ArrayList<DayForecastRow>()

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
        val dayForecastRow = dailyForecastRows[position]
        holder.tvMaxTemp.text = dayForecastRow.maxTemp
        holder.ivMinToMax
        holder.tvMinTemp.text = dayForecastRow.minTemp
        holder.ivWeather.setImageResource(dayForecastRow.iconId)
        holder.tvDate.text = dayForecastRow.date
        holder.tvDayOfWeek.text = dayForecastRow.dayOfWeek
        holder.itemView.setOnClickListener { listener.onDayForecastClick(dayForecastRow) }
    }

    override fun getItemCount(): Int {
        return dailyForecastRows.size
    }

    fun setDailyForecast(dailyForecastRow: List<DayForecastRow>) {
        this.dailyForecastRows.clear()
        this.dailyForecastRows.addAll(dailyForecastRow)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMaxTemp: TextView = itemView.findViewById(R.id.row_day_forecast__tvTempMax)
        val ivMinToMax: ImageView = itemView.findViewById(R.id.row_day_forecast__ivMaxToMin)
        val tvMinTemp: TextView = itemView.findViewById(R.id.row_day_forecast__tvTempMin)
        val ivWeather: ImageView = itemView.findViewById(R.id.row_day_forecast__ivWeather)
        val tvDate: TextView = itemView.findViewById(R.id.row_day_forecast__tvDate)
        val tvDayOfWeek: TextView = itemView.findViewById(R.id.row_day_forecast__tvDayOfWeek)
    }
}