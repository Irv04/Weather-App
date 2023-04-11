package com.irv.weather_app.ui.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irv.weather_app.data.model.CurrentWeatherResponse
import com.irv.weather_app.data.model.ForecastResponse
import com.irv.weather_app.domain.usecase.CurrentWeatherUseCase
import com.irv.weather_app.domain.usecase.ForecastUseCase
import com.irv.weather_app.domain.usecase.SearchForecastUseCase
import com.irv.weather_app.domain.usecase.SearchWeatherUseCase
import com.irv.weather_app.utils.LocationLiveData
import com.irv.weather_app.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val app : Application,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val forecastUseCase : ForecastUseCase,
    private val searchWeatherUseCase: SearchWeatherUseCase,
    private val searchForecastUseCase: SearchForecastUseCase
) : AndroidViewModel (app) {


    private val currentWeather : MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()
    val currentWeatherData : LiveData<Resource<CurrentWeatherResponse>>
    get() = currentWeather

    private val forecast : MutableLiveData<Resource<ForecastResponse>> = MutableLiveData()
    val forecastData : LiveData<Resource<ForecastResponse>>
    get() = forecast

    private var locationLiveData = LocationLiveData(app)
    fun fetchLocationLiveData() = locationLiveData


    fun getCurrentWeather(latitude: Double, longitude: Double) = viewModelScope.launch(Dispatchers.IO) {
        currentWeather.postValue(Resource.Loading())
        try {
            if(isNetworkAvailable(app)){
                val result = currentWeatherUseCase.execute(latitude, longitude)
                currentWeather.postValue(result)
            }else{
                currentWeather.postValue(Resource.Error("Network not available"))
            }
        }
        catch (e: Exception){
            currentWeather.postValue(Resource.Error(e.message.toString()))
        }

    }

    fun getForecast(latitude: Double, longitude: Double) = viewModelScope.launch(Dispatchers.IO) {
        forecast.postValue(Resource.Loading())
        try {
            if(isNetworkAvailable(app)){
                val result = forecastUseCase.execute(latitude, longitude)
                forecast.postValue(result)
            }else{
                forecast.postValue(Resource.Error("Network not available"))
            }
        }
        catch (e: Exception){
            forecast.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun searchWeather(query: String) = viewModelScope.launch(Dispatchers.IO) {
        currentWeather.postValue(Resource.Loading())
        try {
            if(isNetworkAvailable(app)){
                val result = searchWeatherUseCase.execute(query)
                currentWeather.postValue(result)
            }else{
                currentWeather.postValue(Resource.Error("Network not available"))
            }
        }
        catch (e: Exception){
            currentWeather.postValue(Resource.Error(e.message.toString()))
        }

    }

    fun searchForecast(query: String) = viewModelScope.launch(Dispatchers.IO) {
        forecast.postValue(Resource.Loading())
        try {
            if(isNetworkAvailable(app)){
                val result = searchForecastUseCase.execute(query)
                forecast.postValue(result)
            }else{
                forecast.postValue(Resource.Error("Network not available"))
            }
        }
        catch (e: Exception){
            forecast.postValue(Resource.Error(e.message.toString()))
        }
    }


    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }


}