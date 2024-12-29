package com.miluconnect.profeliomp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

private var isKoinStarted = false

fun initKoin(vararg platformModules: Module) {
    if (!isKoinStarted) {
        println("KOIN !isKoinStarted()")
        startKoin {
            modules(sharedModule, *platformModules)
            println("KOIN modules(sharedModule, *extraModules)")
        }
        isKoinStarted = true
    }
}

