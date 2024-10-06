package org.example.rickyandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.example.rickyandmorty.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferenceDao {
    @Query("SELECT * FROM CharacterOfTheDayEntity")
    suspend fun getCharacterOfTheDayDb(): CharacterOfTheDayEntity?

    @Insert(entity = CharacterOfTheDayEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(characterOfTheDayEntity: CharacterOfTheDayEntity)
}