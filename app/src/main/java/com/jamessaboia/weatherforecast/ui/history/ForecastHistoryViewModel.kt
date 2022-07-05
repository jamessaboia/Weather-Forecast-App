package com.jamessaboia.weatherforecast.ui.history

import androidx.lifecycle.MutableLiveData
import com.jamessaboia.weatherforecast.base.BaseViewModel
import com.jamessaboia.weatherforecast.data.db.entity.History
import com.jamessaboia.weatherforecast.data.repository.ForecastRepository
import kotlinx.coroutines.launch

class ForecastHistoryViewModel(
    private val forecastRepository: ForecastRepository
) : BaseViewModel() {

    var listHistory: MutableLiveData<List<History>> = MutableLiveData()

    fun getHistory() {
        launch {
            try {
                val teste = forecastRepository.getForecast()
                listHistory.postValue(teste)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}