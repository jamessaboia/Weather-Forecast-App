package com.jamessaboia.weatherforecast

import android.app.Application
import com.jamessaboia.weatherforecast.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ForecastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@ForecastApplication)
        modules(appComponent)
    }
}