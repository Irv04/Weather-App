package com.irv.weather_app.data.datasource

import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.db.dao.ForecastDao
import com.irv.weather_app.db.entity.ForecastEntity
import kotlinx.coroutines.flow.Flow

class WeatherLocalDataSourceImpl(
    private val forecastDao: ForecastDao
) : WeatherLocalDataSource{

    override fun insertForecast(forecast: ForecastResponse) {
        forecastDao.deleteAndInsert(ForecastEntity(forecast))
    }

    override fun getForecast(): Flow<ForecastEntity> {
        return forecastDao.getForecast()
    }
}