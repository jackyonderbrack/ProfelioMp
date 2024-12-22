package com.miluconnect.profeliomp

import android.app.Application
import com.miluconnect.profeliomp.di.initKoinAndroid

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoinAndroid(this)
    }
}