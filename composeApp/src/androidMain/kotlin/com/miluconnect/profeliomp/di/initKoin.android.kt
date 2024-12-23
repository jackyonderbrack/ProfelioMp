package com.miluconnect.profeliomp.di

import android.content.Context
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoinAndroid() {
    initCommonKoin(androidModule)
    println("KOIN initKoinAndroid()")
}