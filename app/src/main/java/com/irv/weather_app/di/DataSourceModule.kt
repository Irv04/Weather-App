package com.irv.weather_app.di

import com.irv.weather_app.data.OpenWeatherAPI
import com.irv.weather_app.data.datasource.WeatherDataSource
import com.irv.weather_app.data.datasource.WeatherDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(openWeatherAPI: OpenWeatherAPI) : WeatherDataSource {
        return WeatherDataSourceImpl(openWeatherAPI)
    }
}