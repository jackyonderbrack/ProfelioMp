package com.miluconnect.profeliomp.di

import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule: Module = module {
    singleOf(::LoginViewModel)
}