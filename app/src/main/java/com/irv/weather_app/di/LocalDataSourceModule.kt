package com.irv.weather_app.di

import com.irv.weather_app.data.datasource.WeatherLocalDataSource
import com.irv.weather_app.data.datasource.WeatherLocalDataSourceImpl
import com.irv.weather_app.db.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideWeatherLocalDataSource(forecastDao: ForecastDao) : WeatherLocalDataSource{
        return WeatherLocalDataSourceImpl(forecastDao)
    }
}