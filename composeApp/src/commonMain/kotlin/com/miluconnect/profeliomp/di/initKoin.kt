package com.miluconnect.profeliomp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}, platformModule: Module) {
    startKoin {
        appDeclaration()
        modules(listOf(platformModule, sharedModule))
    }
}
