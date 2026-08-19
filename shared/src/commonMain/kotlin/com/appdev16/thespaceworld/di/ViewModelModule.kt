package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.presentation.screens.events.EventsViewModel
import com.appdev16.thespaceworld.presentation.screens.events.detail.EventDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesViewModel
import com.appdev16.thespaceworld.presentation.screens.launches.detail.LaunchDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LaunchesViewModel(get()) }
    viewModel { (launchId: String) -> LaunchDetailViewModel(get(), launchId) }
    viewModel { EventsViewModel(get()) }
    viewModel { (eventId: Int) -> EventDetailViewModel(get(), eventId) }
}
