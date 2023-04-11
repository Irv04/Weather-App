package com.irv.weather_app.di

import com.irv.weather_app.ui.details.HourlyAdapter
import com.irv.weather_app.ui.home.ForecastAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideForecastAdater() : ForecastAdapter {
        return ForecastAdapter()
    }

    @Provides
    @Singleton
    fun provideHourlyAdater() : HourlyAdapter{
        return HourlyAdapter()
    }
}