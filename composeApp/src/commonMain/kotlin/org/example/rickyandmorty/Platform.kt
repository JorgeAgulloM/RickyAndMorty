package org.example.rickyandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform