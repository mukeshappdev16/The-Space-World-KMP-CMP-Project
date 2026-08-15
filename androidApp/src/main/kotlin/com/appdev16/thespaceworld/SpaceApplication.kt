package com.appdev16.thespaceworld

import android.app.Application
import com.appdev16.thespaceworld.di.initKoin
import org.koin.android.ext.koin.androidContext

class SpaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SpaceApplication)
        }
    }
}
