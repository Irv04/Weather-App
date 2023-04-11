package com.irv.weather_app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp")
    val temp: Double?,

    @Json(name = "temp_min")
    var tempMin: Double?,

    @Json(name = "temp_max")
    var tempMax: Double?,

    @Json(name = "humidity")
    val humidity: Int?,

    @Json(name = "pressure")
    val pressure: Double?

) : Parcelable {

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getTempMinString(): String {
        return "Min." + tempMin.toString().substringBefore(".") + "°"
    }

    fun getTempMaxString(): String {
        return "Max." + tempMax.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return humidity.toString() + "°"
    }
}
