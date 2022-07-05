package com.jamessaboia.weatherforecast.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.preference.PreferenceManager


object PreferenceHelper {

    const val LOCATION = "prefs_location"
    const val DEFAULT_CITY = "Manaus"

    fun getStringPreference(context: Context, key: String?): String? {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.getString(key, null)
        return preferences.getString(key, null)
    }

     fun getLocation(context: Context): String {
        return getStringPreference(context, LOCATION) ?: DEFAULT_CITY
    }

}