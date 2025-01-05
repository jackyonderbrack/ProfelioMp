package com.miluconnect.profeliomp

import android.app.Application
import com.miluconnect.profeliomp.di.androidModule
import com.miluconnect.profeliomp.di.initKoin
import org.koin.android.ext.koin.androidContext

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(androidModule) {
            androidContext(this@Application)
        }
    }
}