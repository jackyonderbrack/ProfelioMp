package com.miluconnect.profeliomp.di

import com.miluconnect.profeliomp.AppViewModel
import com.miluconnect.profeliomp.data.core.HttpClientFactory
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteUserDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteUserDataSource
import com.miluconnect.profeliomp.data.repository.login.LoginRepository
import com.miluconnect.profeliomp.data.repository.login.LoginRepositoryImpl
import com.miluconnect.profeliomp.data.repository.user.UserRepository
import com.miluconnect.profeliomp.data.repository.user.UserRepositoryImpl
import com.miluconnect.profeliomp.presentation.screens.account.AccountViewModel
import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule: Module = module {
    // HttpClient
    single { HttpClientFactory.create(get()) }

    // Data sources
    singleOf(::RemoteLoginDataSourceImpl).bind<RemoteLoginDataSource>()
    singleOf(::RemoteUserDataSourceImpl).bind<RemoteUserDataSource>()

    // Repositories
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::UserRepositoryImpl).bind<UserRepository>()

    // ViewModels
    viewModelOf(::AppViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::AccountViewModel)
}


expect val platformModule: Module