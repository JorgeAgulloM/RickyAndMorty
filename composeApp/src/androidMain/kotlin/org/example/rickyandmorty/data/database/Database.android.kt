package org.example.rickyandmorty.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabase(context: Context): RickyAndMortyDatabase {
    val dbFile = context.getDatabasePath(DATABASE_NAME).absolutePath
    return Room.databaseBuilder<RickyAndMortyDatabase>(context, dbFile)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}