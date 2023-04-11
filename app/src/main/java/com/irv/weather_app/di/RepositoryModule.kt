package com.irv.weather_app.di

import com.irv.weather_app.data.datasource.WeatherDataSource
import com.irv.weather_app.data.datasource.WeatherLocalDataSource
import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.domain.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherDataSource: WeatherDataSource, weatherLocalDataSource: WeatherLocalDataSource) : WeatherRepository {
        return WeatherRepositoryImpl(weatherDataSource, weatherLocalDataSource)
    }
}