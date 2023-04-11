package com.irv.weather_app.domain.repository

import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.db.entity.ForecastEntity
import com.irv.weather_app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double) : Resource<CurrentWeatherResponse>
    suspend fun getForecast(latitude: Double, longitude: Double) : Resource<ForecastResponse>
    suspend fun searchWeather(query: String) : Resource<CurrentWeatherResponse>
    suspend fun searchForecast(query: String) : Resource<ForecastResponse>
    fun insertForecast(forecastResponse: ForecastResponse)
    fun getLocalForecast() : Flow<ForecastEntity>

}