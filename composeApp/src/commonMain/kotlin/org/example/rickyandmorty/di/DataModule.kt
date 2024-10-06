package org.example.rickyandmorty.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.rickyandmorty.data.RepositoryImpl
import org.example.rickyandmorty.data.remote.ApiService
import org.example.rickyandmorty.data.remote.paging.CharactersPagingSource
import org.example.rickyandmorty.domain.Repository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                // Al utilziar  ignoreUnknownKeys evitamos que reviente si llega algún valor desconocido que no tenemos implementado
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                    // parameters.append("api_key", "123456789") Si necesita apiKey
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory<Repository> { RepositoryImpl(get()) }
    factoryOf(::CharactersPagingSource)
}
