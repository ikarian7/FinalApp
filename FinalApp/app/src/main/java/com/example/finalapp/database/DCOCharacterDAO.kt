package com.example.finalapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalapp.model.DCOCharacter
import com.example.finalapp.model.WeaponItem

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

    //QUIRKS
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET quirk = :quirk WHERE id = :currentDcoCharacter")
    suspend fun updateQuirk(currentDcoCharacter: Int, quirk: String)

    //GET
    @Query("SELECT quirk FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getQuirk(currentDcoCharacter: Int): LiveData<String>

    //CONFLICTS
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET conflictBar = :conflicts WHERE id = :currentDcoCharacter")
    suspend fun updateConflicts(currentDcoCharacter: Int, conflicts: Int)

    //GET
    @Query("SELECT conflictBar FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getConflict(currentDcoCharacter: Int): LiveData<Int>

    //STORIES
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET story = :story WHERE id = :currentDcoCharacter")
    suspend fun updateStories(currentDcoCharacter: Int, story: String)

    //GET
    @Query("SELECT story FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getStory(currentDcoCharacter: Int): LiveData<String>

    //REWARDS
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET rewards = :rewards WHERE id = :currentDcoCharacter")
    suspend fun updateRewards(currentDcoCharacter: Int, rewards: String)

    //GET
    @Query("SELECT rewards FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getRewards(currentDcoCharacter: Int): LiveData<String>

    //BACKGROUND
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET backGround = :backGround WHERE id = :currentDcoCharacter")
    suspend fun updateBackground(currentDcoCharacter: Int, backGround: String)

    //GET
    @Query("SELECT background FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getBackground(currentDcoCharacter: Int): LiveData<String>

    //LUCK
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET luck = :luck WHERE id = :currentDcoCharacter")
    suspend fun updateLuck(currentDcoCharacter: Int, luck: Int)

    //GET
    @Query("SELECT luck FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getLuck(currentDcoCharacter: Int): LiveData<Int>

    //WOUNDS
    //UPDATE
    @Query("UPDATE dcoCharacterTable SET wounds = :wounds WHERE id = :currentDcoCharacter")
    suspend fun updateWounds(currentDcoCharacter: Int, wounds: Int)

    //GET
    @Query ("SELECT wounds FROM dcoCharacterTable WHERE id = :currentDcoCharacter")
    fun getWounds(currentDcoCharacter: Int): LiveData<Int>

    @Insert
    suspend fun insertWeapon(weaponItem: WeaponItem)

    @Query("SELECT * FROM weaponItemTable WHERE characterID = :currentDcoCharacter")
    fun getAllWeapons(currentDcoCharacter: Int): LiveData<List<WeaponItem>>
}
