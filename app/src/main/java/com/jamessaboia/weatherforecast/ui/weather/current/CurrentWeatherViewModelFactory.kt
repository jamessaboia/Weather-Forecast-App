package com.jamessaboia.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jamessaboia.weatherforecast.data.provider.UnitProvide
import com.jamessaboia.weatherforecast.data.repository.ForecastRepository

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvide: UnitProvide
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository, unitProvide) as T
    }
}