package com.irv.weather_app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Wind(

    @Json(name = "deg")
    val deg: Double?,

    @Json(name = "speed")
    val speed: Double?
) : Parcelable