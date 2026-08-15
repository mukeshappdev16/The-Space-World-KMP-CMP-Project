package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetLaunchesUseCase(get()) }
}
