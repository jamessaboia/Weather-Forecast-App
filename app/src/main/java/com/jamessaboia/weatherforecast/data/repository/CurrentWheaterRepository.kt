package com.jamessaboia.weatherforecast.data.repository

import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jamessaboia.weatherforecast.data.network.response.ForecastResponse


interface CurrentWheaterRepository {
    suspend fun getWeatherLocation(location: String): CurrentWeatherResponse
    suspend fun getForecastNextDays(location: String, days: Int): ForecastResponse
}