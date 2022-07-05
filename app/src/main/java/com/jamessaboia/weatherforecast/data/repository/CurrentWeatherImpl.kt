package com.jamessaboia.weatherforecast.data.repository

import com.jamessaboia.weatherforecast.data.network.Api
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jamessaboia.weatherforecast.data.network.response.ForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrentWeatherImpl(
    private val api: Api
) : CurrentWheaterRepository {

    override suspend fun getWeatherLocation(location: String): CurrentWeatherResponse {
        return withContext(Dispatchers.IO) {
            return@withContext api.getCurrentWeather(location)
        }
    }
    override suspend fun getForecastNextDays(location: String, days : Int): ForecastResponse {
        return withContext(Dispatchers.IO) {
            return@withContext api.getForecastNextDays(location, days)
        }
    }
}