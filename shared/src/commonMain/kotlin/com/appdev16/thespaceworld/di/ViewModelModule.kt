package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.presentation.screens.agencies.AgenciesViewModel
import com.appdev16.thespaceworld.presentation.screens.agencies.detail.AgencyDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.astronauts.AstronautsViewModel
import com.appdev16.thespaceworld.presentation.screens.astronauts.detail.AstronautDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.events.EventsViewModel
import com.appdev16.thespaceworld.presentation.screens.events.detail.EventDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.home.HomeViewModel
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesViewModel
import com.appdev16.thespaceworld.presentation.screens.launches.detail.LaunchDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.spacestations.SpaceStationsViewModel
import com.appdev16.thespaceworld.presentation.screens.spacestations.detail.SpaceStationDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.spacecrafts.SpacecraftsViewModel
import com.appdev16.thespaceworld.presentation.screens.spacecrafts.detail.SpacecraftDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.locations.LocationsViewModel
import com.appdev16.thespaceworld.presentation.screens.locations.detail.LocationDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LaunchesViewModel(get()) }
    viewModel { (launchId: String) -> LaunchDetailViewModel(get(), launchId) }
    viewModel { EventsViewModel(get()) }
    viewModel { (eventId: Int) -> EventDetailViewModel(get(), eventId) }
    viewModel { AgenciesViewModel(get()) }
    viewModel { (agencyId: Int) -> AgencyDetailViewModel(get(), agencyId) }
    viewModel { AstronautsViewModel(get()) }
    viewModel { (astronautId: Int) -> AstronautDetailViewModel(get(), astronautId) }
    viewModel { SpaceStationsViewModel(get()) }
    viewModel { (stationId: Int) -> SpaceStationDetailViewModel(get(), stationId) }
    viewModel { SpacecraftsViewModel(get()) }
    viewModel { (spacecraftId: Int) -> SpacecraftDetailViewModel(get(), spacecraftId) }
    viewModel { LocationsViewModel(get()) }
    viewModel { (locationId: Int) -> LocationDetailViewModel(get(), locationId) }
}
