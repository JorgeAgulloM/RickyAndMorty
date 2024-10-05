package org.example.rickyandmorty.di

import org.example.rickyandmorty.domain.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)
}
