package com.jamessaboia.weatherforecast.data.provider

import com.jamessaboia.weatherforecast.internal.UnitSystem

interface UnitProvide {
    fun getUnitSystem(): UnitSystem
}