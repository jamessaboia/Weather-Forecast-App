package com.jamessaboia.weatherforecast.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.jamessaboia.weatherforecast.data.db.Converters

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(

    @SerializedName("feelslike_c")
    val feelslike_c: Float,

    @SerializedName("precip_mm")
    val precip_mm: Float,

    @SerializedName("temp_c")
    val temp_c: Int,

    @SerializedName("vis_km")
    val vis_km: Int,

    @SerializedName("condition")
    @Embedded
    @TypeConverters(Converters::class)
    val condition: Condition,

    @SerializedName("wind_mph")
    val wind_mph: Float

) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID

}

@Entity(tableName = "condition")
data class Condition(
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: String
)