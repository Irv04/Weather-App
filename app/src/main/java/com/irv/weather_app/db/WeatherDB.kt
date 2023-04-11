package com.irv.weather_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.irv.weather_app.db.dao.ForecastDao
import com.irv.weather_app.db.entity.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WeatherDB : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao
}