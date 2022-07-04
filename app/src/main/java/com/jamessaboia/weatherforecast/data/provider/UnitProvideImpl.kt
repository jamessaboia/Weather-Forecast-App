package com.jamessaboia.weatherforecast.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jamessaboia.weatherforecast.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProvideImpl(context: Context) : UnitProvide {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}