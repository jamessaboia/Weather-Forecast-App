package com.jamessaboia.weatherforecast.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamessaboia.weatherforecast.data.db.entity.History


@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}
