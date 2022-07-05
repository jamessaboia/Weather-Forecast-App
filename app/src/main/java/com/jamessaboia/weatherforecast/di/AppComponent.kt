package com.jamessaboia.weatherforecast.di

val appComponent = listOf(
    viewModelModule,
    networkModule,
    databaseModule,
    repositoryModule
)