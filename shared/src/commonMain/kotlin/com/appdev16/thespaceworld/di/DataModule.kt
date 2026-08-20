package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.data.database.AppDatabase
import com.appdev16.thespaceworld.data.database.DatabaseBuilder
import com.appdev16.thespaceworld.data.database.getRoomDatabase
import com.appdev16.thespaceworld.data.remote.AgenciesRemoteDataSource
import com.appdev16.thespaceworld.data.remote.AgenciesRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.remote.AstronautsRemoteDataSource
import com.appdev16.thespaceworld.data.remote.AstronautsRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.remote.EventsRemoteDataSource
import com.appdev16.thespaceworld.data.remote.EventsRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSource
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.remote.SpaceStationsRemoteDataSource
import com.appdev16.thespaceworld.data.remote.SpaceStationsRemoteDataSourceImpl
import com.appdev16.thespaceworld.data.repositories.AgenciesRepositoryImpl
import com.appdev16.thespaceworld.data.repositories.AstronautsRepositoryImpl
import com.appdev16.thespaceworld.data.repositories.EventsRepositoryImpl
import com.appdev16.thespaceworld.data.repositories.LaunchesRepositoryImpl
import com.appdev16.thespaceworld.data.repositories.SpaceStationsRepositoryImpl
import com.appdev16.thespaceworld.domain.repositories.AgenciesRepository
import com.appdev16.thespaceworld.domain.repositories.AstronautsRepository
import com.appdev16.thespaceworld.domain.repositories.EventsRepository
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import org.koin.dsl.module

val dataModule = module {
    single { 
        val builder = get<DatabaseBuilder>().setup()
        getRoomDatabase(builder)
    }
    
    single { get<AppDatabase>().launchDao() }
    single { get<AppDatabase>().eventDao() }
    single { get<AppDatabase>().agencyDao() }
    single { get<AppDatabase>().astronautDao() }

    single<LaunchesRemoteDataSource> { LaunchesRemoteDataSourceImpl(get()) }
    single<LaunchesRepository> { LaunchesRepositoryImpl(get(), get()) }

    single<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single<EventsRepository> { EventsRepositoryImpl(get(), get()) }

    single<AgenciesRemoteDataSource> { AgenciesRemoteDataSourceImpl(get()) }
    single<AgenciesRepository> { AgenciesRepositoryImpl(get(), get()) }

    single<AstronautsRemoteDataSource> { AstronautsRemoteDataSourceImpl(get()) }
    single<AstronautsRepository> { AstronautsRepositoryImpl(get(), get()) }

    single<SpaceStationsRemoteDataSource> { SpaceStationsRemoteDataSourceImpl(get()) }
    single<SpaceStationsRepository> { SpaceStationsRepositoryImpl(get()) }
}
