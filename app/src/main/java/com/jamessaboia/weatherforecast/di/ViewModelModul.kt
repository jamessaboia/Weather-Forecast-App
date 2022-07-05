package com.jamessaboia.weatherforecast.di

import com.jamessaboia.weatherforecast.ui.history.ForecastHistoryViewModel
import com.jamessaboia.weatherforecast.ui.home.HomeViewModel
import com.jamessaboia.weatherforecast.ui.nextweather.NextWeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { NextWeatherDetailsViewModel(get()) }
    viewModel { ForecastHistoryViewModel(get()) }
}