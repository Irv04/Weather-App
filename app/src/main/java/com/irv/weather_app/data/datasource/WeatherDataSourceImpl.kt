package com.irv.weather_app.data.datasource

import com.irv.weather_app.data.OpenWeatherAPI
import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import retrofit2.Response

class WeatherDataSourceImpl(
    private val apiService : OpenWeatherAPI
) : WeatherDataSource {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Response<CurrentWeatherResponse> {
        return apiService.getCurrentWeather(latitude, longitude)
    }

    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Response<ForecastResponse> {
        return apiService.getForecast(latitude, longitude)
    }

    override suspend fun searchWeather(query: String): Response<CurrentWeatherResponse> {
        return apiService.searchWeather(query)
    }

    override suspend fun searchForecast(query: String): Response<ForecastResponse> {
        return apiService.searchForecast(query)
    }
}