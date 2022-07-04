package com.jamessaboia.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.jamessaboia.weatherforecast.data.repository.ForecastRepository
import com.jamessaboia.weatherforecast.internal.UnitSystem
import com.jamessaboia.weatherforecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC  // get from settings later

    val isMetric: Boolean
    get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }
}