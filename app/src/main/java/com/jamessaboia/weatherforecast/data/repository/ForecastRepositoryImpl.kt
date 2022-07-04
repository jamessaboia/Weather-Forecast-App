package com.jamessaboia.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.jamessaboia.weatherforecast.data.db.CurrentWeatherDao
import com.jamessaboia.weatherforecast.data.network.WeatherNetworkDataSource
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }
    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherResponse> {
        initWeatherData()
        return withContext(Dispatchers.IO){
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.current)
        }
    }

    private suspend fun initWeatherData() {
//        val lastWeatherLocation = weatherLocationDao.getLocation().value
//
//        if (lastWeatherLocation == null ||
//            locationProvider.hasLocationChanged(lastWeatherLocation)
//        ) {
//            fetchCurrentWeather()
//            return
//        }
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            "Manaus"
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}