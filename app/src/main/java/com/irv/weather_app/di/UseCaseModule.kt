package com.irv.weather_app.di

import com.irv.weather_app.domain.repository.WeatherRepository
import com.irv.weather_app.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(weatherRepository: WeatherRepository) : CurrentWeatherUseCase {
        return CurrentWeatherUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideForecastUseCase(weatherRepository: WeatherRepository) : ForecastUseCase {
        return ForecastUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(weatherRepository: WeatherRepository) : SearchWeatherUseCase {
        return SearchWeatherUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideSearchForecastUseCase(weatherRepository: WeatherRepository) : SearchForecastUseCase {
        return SearchForecastUseCase(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocalForecastUseCase(weatherRepository: WeatherRepository) : GetLocalForecastUseCase {
        return GetLocalForecastUseCase(weatherRepository)
    }
}