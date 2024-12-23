package com.miluconnect.profeliomp.di

import org.koin.core.context.startKoin

fun initKoinIOS() {
    initCommonKoin(iosModule)
    println("KOIN initKoinIOS")
}