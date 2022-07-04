package com.jamessaboia.weatherforecast.data.network.response

import androidx.room.Embedded
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.jamessaboia.weatherforecast.data.db.Converters
import com.jamessaboia.weatherforecast.data.db.entity.CurrentWeatherEntry
import com.jamessaboia.weatherforecast.data.db.entity.Location
import com.jamessaboia.weatherforecast.data.db.entity.Request


data class CurrentWeatherResponse(
    @SerializedName("current")
    @Embedded
    @TypeConverters(Converters::class)
    val current: CurrentWeatherEntry
)