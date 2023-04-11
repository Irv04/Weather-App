package com.irv.weather_app.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.irv.weather_app.data.model.ListItem
import com.irv.weather_app.databinding.ItemHourlyForecastBinding
import com.irv.weather_app.utils.Constants
import com.squareup.picasso.Picasso

class HourlyAdapter : RecyclerView.Adapter<HourlyAdapter.ForecastHourlyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ListItem>(){
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.dtTxt == newItem.dtTxt
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourlyViewHolder {
        val binding = ItemHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastHourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastHourlyViewHolder, position: Int) {
        val listItem = differ.currentList[position]
        holder.bind(listItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ForecastHourlyViewHolder(val binding: ItemHourlyForecastBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(forecast: ListItem){
            binding.dayTextView.text = forecast.getDay()
            binding.hourlyTextView.text = forecast.getHour()
            binding.temperatureTextView.text = forecast.main?.getTempString()
            Picasso.get().load(Constants.IMAGE_URL + (forecast.getWeatherItem()?.icon ?: "") + ".png").into(binding.weatherImageView)
        }
    }



}