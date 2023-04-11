package com.irv.weather_app.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irv.weather_app.domain.usecase.CurrentWeatherUseCase
import com.irv.weather_app.domain.usecase.ForecastUseCase
import com.irv.weather_app.domain.usecase.SearchForecastUseCase
import com.irv.weather_app.domain.usecase.SearchWeatherUseCase

class HomeViewModelFactory  (
    private val app : Application,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val forecastUseCase : ForecastUseCase,
    private val searchWeatherUseCase: SearchWeatherUseCase,
    private val searchForecastUseCase: SearchForecastUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app,
            currentWeatherUseCase,
            forecastUseCase,
            searchWeatherUseCase,
            searchForecastUseCase
        ) as T
    }
}