package com.example.finalapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.DCOCharacter

@Dao
interface DCOCharacterDAO {
    @Insert
    suspend fun insertCharacter(dcoCharacter: DCOCharacter)

    @Query("SELECT * FROM dcoCharacterTable WHERE id = :currentDnDCharacter")
    fun getDCOCharacter(currentDnDCharacter: Int): LiveData<DCOCharacter?>

    @Query("SELECT id FROM dcoCharacterTable WHERE active ORDER BY id LIMIT 1")
    fun getSelectedChara(): LiveData<Int>

    @Query("UPDATE dcoCharacterTable SET active = 1 WHERE id = :chosenCharacterID")
    fun makeActive(chosenCharacterID: Int)

    @Query("UPDATE dcoCharacterTable SET active = 0 WHERE id = :currentCharacterID")
    fun makeInactive(currentCharacterID: Int)

    @Query("SELECT * FROM dcoCharacterTable")
    fun getAllCharacters(): LiveData<List<DCOCharacter>>

    @Delete
    suspend fun deleteChara(dcoCharacter: DCOCharacter)

    @Query("UPDATE dcoCharacterTable SET quirk = :quirk WHERE id = :currentDcoCharacter")
    suspend fun updateQuirk(currentDcoCharacter: Int, quirk: String)

    @Query("SELECT quirk FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getQuirk(currentDcoCharacter: Int): LiveData<String>
}