package com.appdev16.thespaceworld.di

import com.appdev16.thespaceworld.data.database.DatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { DatabaseBuilder(get()) }
}
