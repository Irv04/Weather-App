package com.irv.weather_app.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irv.weather_app.domain.usecase.*

class DetailsViewModelFactory (
    private val getLocalForecastUseCase: GetLocalForecastUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(getLocalForecastUseCase) as T
    }
}