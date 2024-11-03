package org.example.rickyandmorty.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDataBase(): RickyAndMortyDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DATABASE_NAME)
    return Room.databaseBuilder<RickyAndMortyDatabase>(name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
