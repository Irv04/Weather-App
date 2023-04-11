package com.irv.weather_app.domain.repository

import com.irv.weather_app.data.datasource.WeatherDataSource
import com.irv.weather_app.data.datasource.WeatherLocalDataSource
import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.data.model.ListItem
import com.irv.weather_app.db.entity.ForecastEntity
import com.irv.weather_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeatherResponse> {
        return responseToResourceCurrentWeather(weatherDataSource.getCurrentWeather(latitude, longitude))
    }

    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Resource<ForecastResponse> {
        return responseToResourceForecast(weatherDataSource.getForecast(latitude, longitude))
    }

    override suspend fun searchWeather(query: String): Resource<CurrentWeatherResponse> {
        return responseToResourceCurrentWeather(weatherDataSource.searchWeather(query))
    }

    override suspend fun searchForecast(query: String): Resource<ForecastResponse> {
        return responseToResourceForecast(weatherDataSource.searchForecast(query))
    }

    override fun insertForecast(forecastResponse: ForecastResponse) {
        weatherLocalDataSource.insertForecast(forecastResponse)
    }

    override fun getLocalForecast(): Flow<ForecastEntity> {
        return weatherLocalDataSource.getForecast()
    }

    private fun responseToResourceForecast(response: Response<ForecastResponse>) : Resource<ForecastResponse> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                insertForecast(result)
                val filteredList = result.list?.let { mapList(it) }
                result.list = filteredList
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    private fun responseToResourceCurrentWeather(response: Response<CurrentWeatherResponse>) : Resource<CurrentWeatherResponse> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    fun mapList(list: List<ListItem>): List<ListItem> {
        val days = getDays(list)
        val mapped = setMaxAndMinToDays(days, list)
        return mapped
            .distinctBy { it.getDay() }
            .toList()
    }

    fun getDays(list: List<ListItem>) : ArrayList<String>{
        val days = arrayListOf<String>()
        list.forEachIndexed { _, listItem ->
            if (days.contains(listItem.dtTxt?.substringBefore(" ")).not()) {
                listItem.dtTxt?.substringBefore(" ")?.let {
                    days.add(it)
                }
            }
        }

        return days
    }

    fun setMaxAndMinToDays(days: List<String>, list: List<ListItem>): ArrayList<ListItem>{
        val res = arrayListOf<ListItem>()
        days.forEach { day ->
            val array = list.filter { it.getDate().equals(day) }
            val minTemp = array.minByOrNull { it.main?.tempMin ?: 0.0 }?.main?.tempMin
            val maxTemp = array.maxByOrNull { it.main?.tempMax ?: 0.0 }?.main?.tempMax

            array.forEach {
                it.main?.tempMin = minTemp
                it.main?.tempMax = maxTemp
                res.add(it)
            }
        }

        return res
    }
}