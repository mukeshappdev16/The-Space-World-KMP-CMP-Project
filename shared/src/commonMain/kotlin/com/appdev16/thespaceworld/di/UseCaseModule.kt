package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.domain.usecase.GetAgenciesUseCase
import com.appdev16.thespaceworld.domain.usecase.GetAgencyDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetAstronautDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetAstronautsUseCase
import com.appdev16.thespaceworld.domain.usecase.GetEventDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetEventsUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLaunchDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import com.appdev16.thespaceworld.domain.usecase.GetSpaceStationDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetSpaceStationsUseCase
import com.appdev16.thespaceworld.domain.usecase.GetSpacecraftDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetSpacecraftsUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLocationDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLocationsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetLaunchesUseCase(get()) }
    factory { GetLaunchDetailUseCase(get()) }
    factory { GetEventsUseCase(get()) }
    factory { GetEventDetailUseCase(get()) }
    factory { GetAgenciesUseCase(get()) }
    factory { GetAgencyDetailUseCase(get()) }
    factory { GetAstronautsUseCase(get()) }
    factory { GetAstronautDetailUseCase(get()) }
    factory { GetSpaceStationsUseCase(get()) }
    factory { GetSpaceStationDetailUseCase(get()) }
    factory { GetSpacecraftsUseCase(get()) }
    factory { GetSpacecraftDetailUseCase(get()) }
    factory { GetLocationsUseCase(get()) }
    factory { GetLocationDetailUseCase(get()) }
}
