package com.jamessaboia.weatherforecast.data.network.response

import com.jamessaboia.weatherforecast.data.db.entity.CurrentWeatherEntry


data class CurrentWeatherResponse(
    val current: CurrentWeatherEntry
)