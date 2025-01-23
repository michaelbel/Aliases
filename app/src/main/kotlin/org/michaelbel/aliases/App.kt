package org.michaelbel.aliases

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        installLauncherIcon()
    }
}