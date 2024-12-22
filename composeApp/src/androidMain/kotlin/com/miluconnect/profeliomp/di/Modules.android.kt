package com.miluconnect.profeliomp.di

import android.content.Context
import com.miluconnect.profeliomp.data.repository.preferences.initDataStore
import org.koin.dsl.module

val androidModule = module {
    single { initDataStore(get<Context>()) }
}