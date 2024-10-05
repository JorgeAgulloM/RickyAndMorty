package org.example.rickyandmorty.domain

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke() {
        val random: String = (1..826).random().toString()
        val character = repository.getSingleCharacter(random)

    }
}