package com.miluconnect.profeliomp

import com.miluconnect.profeliomp.di.iosModule
import com.miluconnect.profeliomp.di.initKoin
import org.koin.core.context.startKoin

fun initKoinIOS() {
    initKoin(platformModule = iosModule)
}