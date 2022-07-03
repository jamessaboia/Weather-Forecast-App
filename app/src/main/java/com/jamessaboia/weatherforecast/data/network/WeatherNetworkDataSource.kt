package com.jamessaboia.weatherforecast.data.network

import androidx.lifecycle.LiveData
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
//        languageCode: String
    )

}