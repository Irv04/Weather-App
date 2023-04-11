package com.irv.weather_app.data

import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String = Constants.UNITS

    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String = Constants.UNITS
    ): Response<ForecastResponse>

    @GET("weather")
    suspend fun searchWeather(
        @Query("q") location: String,
        @Query("units") units: String = Constants.UNITS
    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun searchForecast(
        @Query("q") location: String,
        @Query("units") units: String = Constants.UNITS
    ): Response<ForecastResponse>


}