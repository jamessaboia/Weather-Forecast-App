package com.jamessaboia.weatherforecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jamessaboia.weatherforecast.data.db.entity.CurrentWeatherEntry


@Database(
    entities = [CurrentWeatherEntry::class], version = 1)

@TypeConverters(value = [(Converters::class)])
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun CurrentWeatherDao(): CurrentWeatherDao

    companion object {
        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also { instance = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java, "forecast.db"
            )
                .build()
    }

}