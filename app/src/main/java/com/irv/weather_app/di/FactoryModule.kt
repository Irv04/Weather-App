package com.irv.weather_app.di

import android.app.Application
import com.irv.weather_app.domain.usecase.*
import com.irv.weather_app.ui.details.DetailsViewModelFactory
import com.irv.weather_app.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideHomeViewModelFactory(app : Application,
                                    currentWeatherUseCase: CurrentWeatherUseCase,
                                    forecastUseCase: ForecastUseCase,
                                    searchWeatherUseCase: SearchWeatherUseCase,
                                    searchForecastUseCase: SearchForecastUseCase
    ) : HomeViewModelFactory {
        return HomeViewModelFactory(app,
            currentWeatherUseCase,
            forecastUseCase,
            searchWeatherUseCase,
            searchForecastUseCase
        )
    }

    @Provides
    @Singleton
    fun provideDetailsViewModelFactory(
        getLocalForecastUseCase: GetLocalForecastUseCase
    ) : DetailsViewModelFactory {
        return DetailsViewModelFactory(getLocalForecastUseCase)
    }

}