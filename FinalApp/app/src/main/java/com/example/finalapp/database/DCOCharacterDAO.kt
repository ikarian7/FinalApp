package com.example.finalapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.DCOCharacter

@Dao
interface DCOCharacterDAO {
    @Insert
    suspend fun insertCharacter(dcoCharacter: DCOCharacter)

    @Query("SELECT * FROM dcoCharacterTable")
    fun getAllCharacters(): LiveData<List<DCOCharacter>>
}