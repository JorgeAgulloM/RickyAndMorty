package org.example.rickyandmorty.di

import org.example.rickyandmorty.data.database.RickyAndMortyDatabase
import org.example.rickyandmorty.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickyAndMortyDatabase> { getDatabase(get()) }
    }
}