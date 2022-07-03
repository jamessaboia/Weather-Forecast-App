package com.jamessaboia.weatherforecast.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String> {

        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)

    }

    @TypeConverter
    fun fromStringList(list: List<String?>): String {
        return Gson().toJson(list)

    }

}