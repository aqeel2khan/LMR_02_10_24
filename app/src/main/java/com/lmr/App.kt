package com.lmr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize timber to provide logging without need to add TAG to logs
        Timber.plant(Timber.DebugTree())
       // initialize()
    }

}