package com.irv.weather_app.db.entity

import android.os.Parcelable
import androidx.room.*
import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.data.model.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Forecast")
data class ForecastEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "list")
    var list: List<ListItem>?
) : Parcelable {

    @Ignore
    constructor(forecastResponse: ForecastResponse) : this(
        id = 0,
        list = forecastResponse.list
    )
}