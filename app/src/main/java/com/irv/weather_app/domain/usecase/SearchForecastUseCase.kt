package com.irv.weather_app.domain.usecase

import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.utils.Resource

class SearchForecastUseCase (private val weatherRepository: WeatherRepository) {

    suspend fun execute(query: String) : Resource<ForecastResponse> {
        return weatherRepository.searchForecast(query)
    }
}