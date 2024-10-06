package org.example.rickyandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import org.example.rickyandmorty.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferenceDao {
    @Query("SELECT * FROM CharacterOfTheDayEntity")
    suspend fun getCharacterOfTheDayDb(): CharacterOfTheDayEntity?
}