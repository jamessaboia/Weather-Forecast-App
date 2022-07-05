package com.jamessaboia.weatherforecast.di

import com.jamessaboia.weatherforecast.data.repository.CurrentWeatherImpl
import com.jamessaboia.weatherforecast.data.repository.CurrentWheaterRepository
import com.jamessaboia.weatherforecast.data.repository.ForecastRepository
import com.jamessaboia.weatherforecast.data.repository.ForecastRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CurrentWheaterRepository> { CurrentWeatherImpl(get()) }
    single<ForecastRepository> { ForecastRepositoryImpl(get()) }
}