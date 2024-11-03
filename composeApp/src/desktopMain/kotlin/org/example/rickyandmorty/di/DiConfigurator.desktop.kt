package org.example.rickyandmorty.di

import org.example.rickyandmorty.data.database.getDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single { getDataBase() }
    }
}