package com.irv.weather_app.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.irv.weather_app.R
import com.irv.weather_app.databinding.FragmentDetailsBinding
import com.irv.weather_app.ui.MainActivity


class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    private lateinit var detailsViewModel: DetailsViewModel

    private lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)
        detailsViewModel =  (activity as MainActivity).detailsViewModel
        hourlyAdapter = (activity as MainActivity).hourlyAdapter

        setCurrentWeather()
        initRecyclerView()
    }

    fun setCurrentWeather(){
        val args : DetailsFragmentArgs by navArgs()
        val forecast = args.forecast
        binding.forecast = forecast

        val day = forecast.getDate()
        day?.let { getForecast(it) }
    }

    fun getForecast(day: String){
        detailsViewModel.getLocalForecast().observe(viewLifecycleOwner) {
            val filteredList = it.list?.filter {
                val dayList = it.getDate()
                dayList == day
            }
            hourlyAdapter.differ.submitList(filteredList)
        }
    }

    fun initRecyclerView(){
        binding.hourlyRecyclerView.apply {
            adapter = hourlyAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}