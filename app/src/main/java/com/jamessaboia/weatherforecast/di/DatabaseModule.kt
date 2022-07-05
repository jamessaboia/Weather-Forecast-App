package com.jamessaboia.weatherforecast.di

import androidx.room.Room
import com.jamessaboia.weatherforecast.data.db.ForecastDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ForecastDatabase::class.java,
            "forecast.db"
        ).build()
    }

    single {
        get<ForecastDatabase>().forecastDao()
    }
}