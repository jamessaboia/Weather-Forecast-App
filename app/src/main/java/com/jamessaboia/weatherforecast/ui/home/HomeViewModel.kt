package com.jamessaboia.weatherforecast.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.base.BaseViewModel
import com.jamessaboia.weatherforecast.data.db.entity.History
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jamessaboia.weatherforecast.data.repository.CurrentWheaterRepository
import com.jamessaboia.weatherforecast.data.repository.ForecastRepository
import com.jamessaboia.weatherforecast.utils.NetworkResponse
import com.jamessaboia.weatherforecast.utils.hasInternet
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel(
    private val currentWheaterRepository: CurrentWheaterRepository,
    private val forecastRepository: ForecastRepository
) : BaseViewModel() {

    var currentWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()

    fun getCurrentWeather(context: Context, location: String) = liveData(IO) {
        emit(NetworkResponse.Loading)
        if (hasInternet(context)) {
            try {
                emit(
                    NetworkResponse.Success(
                        data = currentWheaterRepository.getWeatherLocation(
                            location
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

    fun addHistory(history: History) {
        launch {
            try {
                forecastRepository.addForecast(history)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}