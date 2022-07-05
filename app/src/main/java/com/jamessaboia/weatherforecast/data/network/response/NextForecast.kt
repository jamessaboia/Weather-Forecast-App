package com.jamessaboia.weatherforecast.data.db.entity


import com.google.gson.annotations.SerializedName

data class CurrentWeatherEntry(

    @SerializedName("feelslike_c")
    val feelslike_c: Float,

    @SerializedName("precip_mm")
    val precip_mm: Float,

    @SerializedName("temp_c")
    val temp_c: Float,

    @SerializedName("vis_km")
    val vis_km: Int,

    @SerializedName("condition")
    val condition: Condition,

    @SerializedName("wind_mph")
    val wind_mph: Float

)

data class Condition(
    val text: String,
    val icon: String
)