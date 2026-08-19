package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.data.database.AppDatabase
import com.appdev16.thespaceworld.data.database.DatabaseBuilder
import com.appdev16.thespaceworld.data.database.getRoomDatabase
import com.appdev16.thespaceworld.data.remote.EventsRemoteDataSource
import com.appdev16.thespaceworld.data.remote.EventsRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSource
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.repositories.EventsRepositoryImpl
import com.appdev16.thespaceworld.data.repositories.LaunchesRepositoryImpl
import com.appdev16.thespaceworld.domain.repositories.EventsRepository
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import org.koin.dsl.module

val dataModule = module {
    single { 
        val builder = get<DatabaseBuilder>().setup()
        getRoomDatabase(builder)
    }
    
    single { get<AppDatabase>().launchDao() }
    single { get<AppDatabase>().eventDao() }

    single<LaunchesRemoteDataSource> { LaunchesRemoteDataSourceImpl(get()) }
    single<LaunchesRepository> { LaunchesRepositoryImpl(get(), get()) }

    single<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single<EventsRepository> { EventsRepositoryImpl(get(), get()) }
}
