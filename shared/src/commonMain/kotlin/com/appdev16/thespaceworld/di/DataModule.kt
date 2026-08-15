package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSource
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.repositories.LaunchesRepositoryImpl
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import org.koin.dsl.module

val dataModule = module {
    single<LaunchesRemoteDataSource> { LaunchesRemoteDataSourceImpl(get()) }
    single<LaunchesRepository> { LaunchesRepositoryImpl(get()) }
}
