package com.irv.weather_app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class ListItem(

    @Json(name = "dt")
    val dt: Long?,

    @Json(name = "dt_txt")
    val dtTxt: String?,

    @Json(name = "weather")
    val weather: List<Weather?>?,

    @Json(name = "main")
    val main: Main?,

    @Json(name = "sys")
    val sys: Sys?,

    @Json(name = "wind")
    val wind: Wind?,

) : Parcelable {

    fun getWeatherItem(): Weather? {
        return weather?.first()
    }

    fun getDate() : String?{
        return dtTxt?.split(" ")?.get(0)
    }

    fun getHour(): String? {
        return dtTxt?.split(" ")?.get(1)
    }

    fun getDay(): String? {
        val date = getDate()
        val dt = LocalDate.parse(date)
        return dt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

}