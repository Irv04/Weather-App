package com.irv.weather_app.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.irv.weather_app.R
import com.irv.weather_app.databinding.FragmentMainBinding
import com.irv.weather_app.ui.MainActivity
import com.irv.weather_app.utils.GPSUtil
import com.irv.weather_app.utils.Resource
import com.irv.weather_app.utils.observeOnce

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var forecastAdapter: ForecastAdapter

    private var isGPSEnabled = false
    private var calledOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GPSUtil(requireContext()).turnGPSOn(object : GPSUtil.OnGpsListener {
            override fun gpsStatus(isGPSEnabled: Boolean) {
                this@MainFragment.isGPSEnabled = isGPSEnabled
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = (activity as MainActivity).viewModel
        forecastAdapter = (activity as MainActivity).forecastAdapter
        forecastAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("forecast", it)
            }

            findNavController().navigate(
                R.id.action_mainFragment_to_detailsFragment, bundle
            )
        }

        if (!calledOnce) {
            getLocationAndWeather()
            calledOnce = true
        }


        initRecyclerView()
        observeCurrentWeather()
        observeForecast()
        setSearchView()

    }

    private fun getLocationAndWeather(){
        when {
            allPermissionsGranted() -> {
                homeViewModel.fetchLocationLiveData().observeOnce(
                    viewLifecycleOwner
                ) { location ->
                    location.lat?.let { location.lon?.let { it1 -> getCurrentWeather(it, it1) } }
                    location.lat?.let { location.lon?.let { it1 -> getForecast(it, it1) } }
                }
            }

            shouldShowRequestPermissionRationale() -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.location_permission))
                    .setMessage(getString(R.string.access_location_message))
                    .setNegativeButton(
                        getString(R.string.no)
                    ) { _, _ -> requireActivity().finish() }
                    .setPositiveButton(
                        getString(R.string.ask_me)
                    ) { _, _ ->
                        requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
                    }
                    .show()
            }

            !isGPSEnabled -> {
                Toast.makeText(activity, getString(R.string.gps_required_message), Toast.LENGTH_SHORT).show()
            }

            else -> {
                requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
            }
        }
    }

    fun initRecyclerView(){
        binding.forecastRecyclerView.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun setSearchView(){
        binding.citySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.citySearchView.clearFocus()
                if((p0 != null) && !p0.isEmpty()){
                    homeViewModel.searchWeather(p0.toString())
                    homeViewModel.searchForecast(p0.toString())
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        binding.citySearchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                getLocationAndWeather()
                return false
            }

        })

    }

    private fun hideProgressBarCurrentWeather(){
        binding.layoutTodayForecast.progressBar.visibility = View.GONE
    }

    private fun showProgressBarCurrentWeather(){
        binding.layoutTodayForecast.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBarForecast(){
        binding.nextDaysProgressBar.visibility = View.GONE
    }

    private fun showProgressBarForecast(){
        binding.nextDaysProgressBar.visibility = View.VISIBLE
    }


    private fun observeCurrentWeather(){
        homeViewModel.currentWeatherData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBarCurrentWeather()
                    binding.layoutTodayForecast.currentWeather = response.data
                }
                is Resource.Error -> {
                    hideProgressBarCurrentWeather()
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    showProgressBarCurrentWeather()
                }
            }
        }
    }

    private fun observeForecast(){
        homeViewModel.forecastData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBarForecast()
                    forecastAdapter.differ.submitList(response.data?.list)
                }
                is Resource.Error -> {
                    hideProgressBarForecast()
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    showProgressBarForecast()
                }
            }
        }
    }

    private fun getCurrentWeather(latitude: Double, longitude: Double){
        homeViewModel.getCurrentWeather(latitude, longitude)
    }

    private fun getForecast(latitude: Double, longitude: Double){
        homeViewModel.getForecast(latitude, longitude)

    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun shouldShowRequestPermissionRationale() = REQUIRED_PERMISSIONS.all {
        shouldShowRequestPermissionRationale(it)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            getLocationAndWeather()
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        private const val LOCATION_REQUEST_CODE = 123
    }

}