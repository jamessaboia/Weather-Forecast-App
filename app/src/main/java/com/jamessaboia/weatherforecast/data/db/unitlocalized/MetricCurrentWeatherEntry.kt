package com.jamessaboia.weatherforecast.data.db.unitlocalized

import androidx.databinding.adapters.Converters
import androidx.room.ColumnInfo
import androidx.room.TypeConverters

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    val temperature: Int,
    @ColumnInfo(name = "weatherDescriptions")
    @TypeConverters(Converters::class)
    val weatherDescriptions: List<String>,
    @ColumnInfo(name = "weatherIcons")
    @TypeConverters(Converters::class)
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
