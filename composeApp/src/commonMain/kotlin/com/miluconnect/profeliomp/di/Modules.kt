package com.miluconnect.profeliomp.di

import com.miluconnect.profeliomp.AppViewModel
import com.miluconnect.profeliomp.data.core.HttpClientFactory
import com.miluconnect.profeliomp.data.network.RemoteIssueDataSource
import com.miluconnect.profeliomp.data.network.RemoteIssuesDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteOfferDataSource
import com.miluconnect.profeliomp.data.network.RemoteOfferDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteProjectDataSource
import com.miluconnect.profeliomp.data.network.RemoteProjectDataSourceImpl
import com.miluconnect.profeliomp.data.network.RemoteUserDataSource
import com.miluconnect.profeliomp.data.network.RemoteUserDataSourceImpl
import com.miluconnect.profeliomp.data.repository.issue.IssueRepository
import com.miluconnect.profeliomp.data.repository.issue.IssueRepositoryImpl
import com.miluconnect.profeliomp.data.repository.login.LoginRepository
import com.miluconnect.profeliomp.data.repository.login.LoginRepositoryImpl
import com.miluconnect.profeliomp.data.repository.offer.DefaultOfferRepository
import com.miluconnect.profeliomp.data.repository.offer.OfferRepository
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.data.repository.project.ProjectRepositoryImpl
import com.miluconnect.profeliomp.data.repository.user.UserRepository
import com.miluconnect.profeliomp.data.repository.user.UserRepositoryImpl
import com.miluconnect.profeliomp.presentation.screens.account.AccountViewModel
import com.miluconnect.profeliomp.presentation.screens.blackboard.BlackboardViewModel
import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import com.miluconnect.profeliomp.presentation.screens.work.WorkViewModel
import com.miluconnect.profeliomp.presentation.screens.work.screens.addIssue.AddIssueViewModel
import com.miluconnect.profeliomp.presentation.screens.work.screens.addProject.AddProjectViewModel
import com.miluconnect.profeliomp.presentation.screens.work.screens.projectDetail.ProjectDetailViewModel
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
    singleOf(::RemoteOfferDataSourceImpl).bind<RemoteOfferDataSource>()
    singleOf(::RemoteProjectDataSourceImpl).bind<RemoteProjectDataSource>()
    singleOf(::RemoteIssuesDataSourceImpl).bind<RemoteIssueDataSource>()

    // Repositories
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
    singleOf(::DefaultOfferRepository).bind<OfferRepository>()
    singleOf(::ProjectRepositoryImpl).bind<ProjectRepository>()
    singleOf(::IssueRepositoryImpl).bind<IssueRepository>()

    // ViewModels
    viewModelOf(::AppViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::AccountViewModel)
    viewModelOf(::BlackboardViewModel)
    viewModelOf(::WorkViewModel)
    viewModelOf(::AddProjectViewModel)
    viewModelOf(::AddIssueViewModel)
    viewModelOf(::ProjectDetailViewModel)
}

expect val platformModule: Module