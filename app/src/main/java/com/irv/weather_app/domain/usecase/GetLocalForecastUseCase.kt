package com.irv.weather_app.domain.usecase

import com.irv.weather_app.db.entity.ForecastEntity
import com.irv.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetLocalForecastUseCase (private val weatherRepository: WeatherRepository) {

    fun execute() : Flow<ForecastEntity> {
        return weatherRepository.getLocalForecast()
    }

}