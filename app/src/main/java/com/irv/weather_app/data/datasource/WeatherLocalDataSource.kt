package com.irv.weather_app.data.datasource

import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.db.entity.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {

    fun insertForecast(forecast: ForecastResponse)
    fun getForecast() : Flow<ForecastEntity>
}