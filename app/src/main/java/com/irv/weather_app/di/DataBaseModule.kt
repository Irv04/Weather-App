package com.irv.weather_app.di

import android.app.Application
import androidx.room.Room
import com.irv.weather_app.db.WeatherDB
import com.irv.weather_app.db.dao.ForecastDao
import com.irv.weather_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideWeatherDataBase(app: Application) : WeatherDB{
        return Room.databaseBuilder(app, WeatherDB::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(weatherDB: WeatherDB) : ForecastDao {
        return weatherDB.forecastDao()
    }


}