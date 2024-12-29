package com.miluconnect.profeliomp.di

import com.miluconnect.profeliomp.data.core.HttpClientFactory
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteUserDataSoruce
import com.miluconnect.profeliomp.data.network.RemoteUserDataSoruceImpl
import com.miluconnect.profeliomp.data.repository.LoginRepository
import com.miluconnect.profeliomp.data.repository.LoginRepositoryImpl
import com.miluconnect.profeliomp.data.repository.UserRepository
import com.miluconnect.profeliomp.data.repository.UserRepositoryImpl
import com.miluconnect.profeliomp.presentation.screens.account.AccountViewModel
import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule: Module = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteLoginDataSourceImpl).bind<RemoteLoginDataSource>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::RemoteUserDataSoruceImpl).bind<RemoteUserDataSoruce>()
    singleOf(::UserRepositoryImpl).bind<UserRepository>()

    viewModelOf(::LoginViewModel)
    viewModelOf(::AccountViewModel)
}

expect val platformModule: Module