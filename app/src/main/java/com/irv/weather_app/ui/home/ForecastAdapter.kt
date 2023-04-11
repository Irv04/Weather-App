package com.irv.weather_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.irv.weather_app.data.model.ListItem
import com.irv.weather_app.databinding.ItemNextDayForecastBinding
import com.squareup.picasso.Picasso

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ListItem>(){
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.dtTxt == newItem.dtTxt
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemNextDayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val listItem = differ.currentList[position]
        holder.bind(listItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ForecastViewHolder(val binding: ItemNextDayForecastBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(forecast: ListItem){
            binding.dayTextView.text = forecast.getDay()
            binding.maxTemperatureTextView.text = forecast.main?.getTempMinString()
            binding.minTemperatureTextView.text = forecast.main?.getTempMaxString()
            Picasso.get().load(forecast.getWeatherItem()?.getImageUrl()).into(binding.weatherImageView)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(forecast)
                }
            }
        }
    }

    private var onItemClickListener :((ListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ListItem) -> Unit){
        onItemClickListener = listener
    }


}