package com.irv.weather_app.domain.usecase

import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.utils.Resource

class CurrentWeatherUseCase (private val weatherRepository: WeatherRepository) {

    suspend fun execute(latitude: Double, longitude: Double) : Resource<CurrentWeatherResponse> {
        return weatherRepository.getCurrentWeather(latitude, longitude)
    }
}