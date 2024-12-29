package com.miluconnect.profeliomp.di

import org.koin.dsl.module
import com.miluconnect.profeliomp.data.repository.preferences.initDataStore
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module

val iosModule = module {
    single { initDataStore() }
}

actual val platformModule: Module
    get() = module {
        Darwin.create()
    }