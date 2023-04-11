package com.irv.weather_app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Sys(

    @Json(name = "country")
    val country: String?,

    @Json(name = "sunrise")
    val sunrise: Int?,

    @Json(name = "sunset")
    val sunset: Int?,

) : Parcelable