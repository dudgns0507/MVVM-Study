package com.github.dudgns0507.mvvm_cropo

import android.app.Application
import com.github.dudgns0507.core.pref.PrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        PrefManager.initialize(this)
    }
}