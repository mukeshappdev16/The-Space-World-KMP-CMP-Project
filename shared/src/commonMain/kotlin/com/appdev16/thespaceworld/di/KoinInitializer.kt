package com.appdev16.thespaceworld.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            platformModule,
            networkModule,
            dataModule,
            useCaseModule,
            viewModelModule
        )
    }
}

fun initKoinIos() = initKoin()
