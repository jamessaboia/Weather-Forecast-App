package com.jamessaboia.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherResponse>
//    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}