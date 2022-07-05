package com.jamessaboia.weatherforecast.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamessaboia.weatherforecast.data.db.entity.History

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addForecast(forecast: History)

    @Query("select * from history ORDER BY id DESC")
    fun getForecast(): List<History>
}