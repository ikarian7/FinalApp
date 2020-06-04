package com.example.finalapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.finalapp.model.DCOCharacter

@Dao
interface DCOCharacterDAO {
    @Insert
    suspend fun insertCharacter(dcoCharacter: DCOCharacter)
}