package com.irv.weather_app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(

    @Json(name = "weather")
    val weather: List<Weather?>? = null,

    @Json(name = "main")
    val main: Main? = null,

    @Json(name = "visibility")
    val visibility: Int? = null,

    @Json(name = "wind")
    val wind: Wind? = null,

    @Json(name = "sys")
    val sys: Sys? = null,

    @Json(name = "dt")
    val dt: Long? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "cod")
    val cod: Int? = null,

    @Json(name = "id")
    val id: Int? = null,

) : Parcelable {

    fun getWeatherItem(): Weather? {
        return weather?.first()
    }

}
