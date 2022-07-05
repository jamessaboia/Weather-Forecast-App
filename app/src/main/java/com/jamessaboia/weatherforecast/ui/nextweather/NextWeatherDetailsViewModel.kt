package com.jamessaboia.weatherforecast.ui.nextweather

import android.content.Context
import androidx.lifecycle.liveData
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.base.BaseViewModel
import com.jamessaboia.weatherforecast.data.repository.CurrentWheaterRepository
import com.jamessaboia.weatherforecast.utils.NetworkResponse
import com.jamessaboia.weatherforecast.utils.hasInternet
import kotlinx.coroutines.Dispatchers

class NextWeatherDetailsViewModel(
    private val currentWheaterRepository: CurrentWheaterRepository,
) : BaseViewModel() {

    fun getNextWeather(context: Context, location: String) = liveData(Dispatchers.IO) {
        emit(NetworkResponse.Loading)
        if (hasInternet(context)) {
            try {
                emit(
                    NetworkResponse.Success(
                        data = currentWheaterRepository.getForecastNextDays(
                            location, HISTORY_DAYS
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(
                    NetworkResponse.Error(
                        exception = exception.message
                            ?: context.getString(R.string.erro_consulta_api)
                    )
                )
            }
        } else
            emit(NetworkResponse.Error(exception = context.getString(R.string.erro_consulta_api)))
    }

    companion object {
        private const val HISTORY_DAYS = 3
    }
}