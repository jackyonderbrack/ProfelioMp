package com.miluconnect.profeliomp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

private var isKoinStarted = false

fun initKoin(
    vararg platformModules: Module,
    appDeclaration: KoinAppDeclaration = {}
) {
    if (!isKoinStarted) {
        startKoin {
            appDeclaration()
            modules(sharedModule, platformModule, *platformModules)
        }
        isKoinStarted = true
    }
}

