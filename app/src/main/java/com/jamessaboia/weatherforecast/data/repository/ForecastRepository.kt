package com.jamessaboia.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.jamessaboia.weatherforecast.data.db.entity.CurrentWeatherEntry
import com.jamessaboia.weatherforecast.data.db.unitlocalized.MetricCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
//    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}