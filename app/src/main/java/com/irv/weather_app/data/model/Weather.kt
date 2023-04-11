package com.irv.weather_app.data.model

import android.os.Parcelable
import com.irv.weather_app.utils.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "id")
    val id: Int?,

    @Json(name = "main")
    val main: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "icon")
    val icon: String?

) : Parcelable {

    fun getDescriptionText(): String? {
        return description?.replaceFirstChar(Char::uppercase)
    }

    fun getImageUrl(): String {
        return Constants.IMAGE_URL + icon + ".png"
    }
}
