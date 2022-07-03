package com.jamessaboia.weatherforecast.data.db.unitlocalized

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temp")
    val temperature: Int,
    @ColumnInfo(name = "weatherDescriptions")
    val weatherDescriptions: List<String>,
    @ColumnInfo(name = "weatherIcons")
    val weatherIcons: List<String>,
    @ColumnInfo(name = "windSpeed")
    val windSpeed: Int,
    @ColumnInfo(name = "windDir")
    val windDir: String,
    @ColumnInfo(name = "precip")
    val precip: Float,
    @ColumnInfo(name = "feelslike")
    val feelslike: Int,
    @ColumnInfo(name = "visibility")
    val visibility: Int
)
