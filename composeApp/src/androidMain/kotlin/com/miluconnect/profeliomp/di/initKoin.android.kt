package com.miluconnect.profeliomp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoinAndroid(context: Context) {
    startKoin {
        androidContext(context)
        modules(androidModule)
    }
}