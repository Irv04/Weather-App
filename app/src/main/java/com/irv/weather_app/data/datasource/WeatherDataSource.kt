package com.irv.weather_app.data.datasource

import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import retrofit2.Response

interface WeatherDataSource {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double) : Response<CurrentWeatherResponse>
    suspend fun getForecast(latitude: Double, longitude: Double) : Response<ForecastResponse>
    suspend fun searchWeather(query: String) : Response<CurrentWeatherResponse>
    suspend fun searchForecast(query: String) : Response<ForecastResponse>

}