package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.domain.usecase.GetEventDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetEventsUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLaunchDetailUseCase
import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetLaunchesUseCase(get()) }
    factory { GetLaunchDetailUseCase(get()) }
    factory { GetEventsUseCase(get()) }
    factory { GetEventDetailUseCase(get()) }
}
