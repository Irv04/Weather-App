package com.irv.weather_app.domain.usecase

import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.utils.Resource

class ForecastUseCase (private val weatherRepository: WeatherRepository) {

    suspend fun execute(latitude: Double, longitude: Double) : Resource<ForecastResponse> {
        return weatherRepository.getForecast(latitude, longitude)
    }


}