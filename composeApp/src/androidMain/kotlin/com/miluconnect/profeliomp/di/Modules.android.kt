package com.miluconnect.profeliomp.di

import android.content.Context
import com.miluconnect.profeliomp.data.repository.preferences.initDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModule = module {
    single { initDataStore(get<Context>()) }
}

actual val platformModule: Module get() = module {
    single<HttpClientEngine> { OkHttp.create() }
}