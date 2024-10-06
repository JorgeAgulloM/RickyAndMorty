package org.example.rickyandmorty.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.rickyandmorty.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "ricky_and_morty_database.db"

// No es necesario crear los actual, ksp lo hace por debajo aún con error de compilación!!!
expect object RickyAndMortyCTor: RoomDatabaseConstructor<RickyAndMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickyAndMortyCTor::class)
abstract class RickyAndMortyDatabase : RoomDatabase() {
    //abstract fun characterDao(): CharacterDao
}