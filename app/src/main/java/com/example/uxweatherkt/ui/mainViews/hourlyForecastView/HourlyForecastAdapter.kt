package com.example.uxweatherkt.ui.mainViews.hourlyForecastView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.uxweatherkt.R
import com.example.uxweatherkt.presenter.row.HourForecastRow


class HourlyForecastAdapter(var listener: Listener) : RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    interface Listener {
        fun onHourForecastClick(hourForecastRow: HourForecastRow)
    }

    var hourlyForecast = ArrayList<HourForecastRow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_hour_forecast, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hourForecastRow = hourlyForecast[position]
        holder.tvDate.text = hourForecastRow.date
        holder.tvHumidity.text = hourForecastRow.humidity
        holder.tvTemp.text = hourForecastRow.temp
        holder.tvFeelLike.text = hourForecastRow.feelLike
        holder.ivWeather.setImageResource(hourForecastRow.iconId)
    }

    override fun getItemCount(): Int {
        return hourlyForecast.size
    }

    fun setDailyForecast(dailyForecastRow: ArrayList<HourForecastRow>) {
        this.hourlyForecast.clear()
        this.hourlyForecast.addAll(dailyForecastRow)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView
        val tvHumidity: TextView
        val tvTemp: TextView
        val tvFeelLike: TextView
        val ivWeather: ImageView

        init {
            tvDate = itemView.findViewById(R.id.row_hour_forecast__tvDate)
            tvHumidity = itemView.findViewById(R.id.row_hour_forecast__tvHumidity)
            tvTemp = itemView.findViewById(R.id.row_hour_forecast__tvTemp)
            tvFeelLike = itemView.findViewById(R.id.row_hour_forecast__tvFeelLike)
            ivWeather = itemView.findViewById(R.id.row_hour_forecast__ivWeather)
        }
    }
}