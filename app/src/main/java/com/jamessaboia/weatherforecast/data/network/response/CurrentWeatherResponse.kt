package com.jamessaboia.weatherforecast.data.network.response

import com.google.gson.annotations.SerializedName
import com.jamessaboia.weatherforecast.data.db.entity.CurrentWeatherEntry
import com.jamessaboia.weatherforecast.data.db.entity.Location
import com.jamessaboia.weatherforecast.data.db.entity.Request


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)