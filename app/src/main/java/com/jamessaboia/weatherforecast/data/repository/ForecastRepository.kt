package com.jamessaboia.weatherforecast.data.repository

import com.jamessaboia.weatherforecast.data.db.entity.History

interface ForecastRepository {
    suspend fun addForecast(history: History)
    suspend fun getForecast(): List<History>
}