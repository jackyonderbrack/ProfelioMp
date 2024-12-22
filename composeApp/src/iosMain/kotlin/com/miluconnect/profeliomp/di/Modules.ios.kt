package com.miluconnect.profeliomp.di

import org.koin.dsl.module
import com.miluconnect.profeliomp.data.repository.preferences.initDataStore

val iosModule = module {
    single { initDataStore() }
}