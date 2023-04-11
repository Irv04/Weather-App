package com.irv.weather_app.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.irv.weather_app.domain.usecase.GetLocalForecastUseCase

class DetailsViewModel(
    private val getLocalForecastUseCase: GetLocalForecastUseCase
) : ViewModel(){

    fun getLocalForecast() = liveData{
        getLocalForecastUseCase.execute().collect {
            emit(it)
        }
    }


}