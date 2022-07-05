package com.jamessaboia.weatherforecast.data.repository

import com.jamessaboia.weatherforecast.data.db.ForecastDao
import com.jamessaboia.weatherforecast.data.db.entity.History

class ForecastRepositoryImpl(
    private val forecastDao: ForecastDao
) : ForecastRepository {

    override suspend fun addForecast(history: History) {
        return forecastDao.addForecast(history)
    }

    override suspend fun getForecast(): List<History> {
        return forecastDao.getForecast()
    }
}