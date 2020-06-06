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
        val dayForecastRows = dailyForecastRows[position]
        holder.tvMaxTemp.text = dayForecastRows.maxTemp
        holder.ivMinToMax
        holder.tvMinTemp.text = dayForecastRows.minTemp
        holder.ivWeather.setImageResource(dayForecastRows.iconId)
        holder.tvDate.text = dayForecastRows.date
        holder.tvDayOfWeek.text = dayForecastRows.dayOfWeek
        holder.itemView.setOnClickListener { listener.onDayForecastClick(dayForecastRows) }
    }

    override fun getItemCount(): Int {
        return dailyForecastRows.size
    }

    fun setDailyForecast(dailyForecastRow: ArrayList<DayForecastRow>) {
        this.dailyForecastRows.clear()
        this.dailyForecastRows.addAll(dailyForecastRow)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMaxTemp: TextView
        val ivMinToMax: ImageView
        val tvMinTemp: TextView
        val ivWeather: ImageView
        val tvDate: TextView
        val tvDayOfWeek: TextView

        init {
            tvMaxTemp = itemView.findViewById(R.id.row_day_forecast__tvTempMax)
            ivMinToMax = itemView.findViewById(R.id.row_day_forecast__ivMaxToMin)
            tvMinTemp = itemView.findViewById(R.id.row_day_forecast__tvTempMin)
            ivWeather = itemView.findViewById(R.id.row_day_forecast__ivWeather)
            tvDate = itemView.findViewById(R.id.row_day_forecast__tvDate)
            tvDayOfWeek = itemView.findViewById(R.id.row_day_forecast__tvDayOfWeek)
        }
    }
}