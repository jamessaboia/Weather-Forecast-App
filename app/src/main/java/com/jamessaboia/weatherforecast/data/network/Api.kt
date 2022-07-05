package com.jamessaboia.weatherforecast.data.network

import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jamessaboia.weatherforecast.data.network.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "a7856b0446aa495499e55850220307"

interface Api {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun getForecastNextDays(
        @Query("q") location: String,  @Query("days") days: Int
    ): ForecastResponse
}