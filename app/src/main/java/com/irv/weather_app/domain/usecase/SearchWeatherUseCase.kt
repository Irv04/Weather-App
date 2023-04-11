package com.irv.weather_app.domain.usecase

import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.utils.Resource

class SearchWeatherUseCase  (private val weatherRepository: WeatherRepository) {

    suspend fun execute(query: String) : Resource<CurrentWeatherResponse> {
        return weatherRepository.searchWeather(query)
    }
}