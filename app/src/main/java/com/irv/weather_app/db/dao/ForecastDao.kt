package com.irv.weather_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.irv.weather_app.db.entity.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Query("SELECT * FROM Forecast")
    fun getForecast(): Flow<ForecastEntity>

    @Query("DELETE FROM Forecast")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun insert(forecast: ForecastEntity)

    @Transaction
    fun deleteAndInsert(forecast: ForecastEntity) {
        deleteAll()
        insert(forecast)
    }


}