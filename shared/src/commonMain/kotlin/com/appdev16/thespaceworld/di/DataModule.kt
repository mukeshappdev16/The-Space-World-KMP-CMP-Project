package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.data.database.AppDatabase
import com.appdev16.thespaceworld.data.database.DatabaseBuilder
import com.appdev16.thespaceworld.data.database.getRoomDatabase
import com.appdev16.thespaceworld.data.remote.*
import com.appdev16.thespaceworld.data.repositories.*
import com.appdev16.thespaceworld.domain.repositories.*
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
    single { get<AppDatabase>().spaceStationDao() }
    single { get<AppDatabase>().spacecraftDao() }
    single { get<AppDatabase>().locationDao() }

    single<LaunchesRemoteDataSource> { LaunchesRemoteDataSourceImpl(get()) }
    single<LaunchesRepository> { LaunchesRepositoryImpl(get(), get()) }

    single<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single<EventsRepository> { EventsRepositoryImpl(get(), get()) }

    single<AgenciesRemoteDataSource> { AgenciesRemoteDataSourceImpl(get()) }
    single<AgenciesRepository> { AgenciesRepositoryImpl(get(), get()) }

    single<AstronautsRemoteDataSource> { AstronautsRemoteDataSourceImpl(get()) }
    single<AstronautsRepository> { AstronautsRepositoryImpl(get(), get()) }

    single<SpaceStationsRemoteDataSource> { SpaceStationsRemoteDataSourceImpl(get()) }
    single<SpaceStationsRepository> { SpaceStationsRepositoryImpl(get(), get()) }

    single<SpacecraftsRemoteDataSource> { SpacecraftsRemoteDataSourceImpl(get()) }
    single<SpacecraftsRepository> { SpacecraftsRepositoryImpl(get(), get()) }

    single<LocationsRemoteDataSource> { LocationsRemoteDataSourceImpl(get()) }
    single<LocationsRepository> { LocationsRepositoryImpl(get(), get()) }
}
