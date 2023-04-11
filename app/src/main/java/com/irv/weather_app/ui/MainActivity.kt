package com.irv.weather_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.irv.weather_app.R
import com.irv.weather_app.databinding.ActivityMainBinding
import com.irv.weather_app.ui.details.DetailsViewModel
import com.irv.weather_app.ui.details.DetailsViewModelFactory
import com.irv.weather_app.ui.details.HourlyAdapter
import com.irv.weather_app.ui.home.HomeViewModel
import com.irv.weather_app.ui.home.HomeViewModelFactory
import com.irv.weather_app.ui.home.ForecastAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var detailsViewModelFactory: DetailsViewModelFactory

    @Inject
    lateinit var forecastAdapter: ForecastAdapter

    @Inject
    lateinit var hourlyAdapter: HourlyAdapter

    lateinit var viewModel : HomeViewModel
    lateinit var detailsViewModel: DetailsViewModel

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        detailsViewModel = ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}