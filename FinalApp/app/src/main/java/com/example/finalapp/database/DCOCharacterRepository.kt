package com.example.finalapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.example.finalapp.model.DCOCharacter

class DCOCharacterRepository(context: Context) {
    private val dcoCharacterDAO: DCOCharacterDAO

    init {
        val database = DCODatabase.getDatabase((context))
        dcoCharacterDAO = database!!.dcoCharacterDAO()
    }

    suspend fun insertCharacter(dcoCharacter: DCOCharacter){
        dcoCharacterDAO.insertCharacter(dcoCharacter)
    }


    fun getDCOCharacter(currentDCOCharacter: Int): LiveData<DCOCharacter?> {
        return dcoCharacterDAO.getDCOCharacter(currentDCOCharacter)
    }


    fun getSelectedChara(): LiveData<Int> {
        return dcoCharacterDAO.getSelectedChara()
    }

    @Transaction
    suspend fun changeSelectedChara(chosenCharacterID: Int, currentCharacterID: Int) {
        dcoCharacterDAO.makeActive(chosenCharacterID)
        dcoCharacterDAO.makeInactive(currentCharacterID)
    }

    fun getAllCharacters(): LiveData<List<DCOCharacter>> {
        return dcoCharacterDAO.getAllCharacters()
    }

    //Deletion of the character
    suspend fun deleteChara(dcoCharacter: DCOCharacter) {
        dcoCharacterDAO.deleteChara(dcoCharacter)
    }

    //UPDATE QUIRK
    suspend fun updateQuirk(dcoCharacter: Int, quirk: String){
        dcoCharacterDAO.updateQuirk(dcoCharacter, quirk)
    }

    //GET QUIRK
    fun getQuirk(currentDCOCharacter: Int): LiveData<String> {
        return dcoCharacterDAO.getQuirk(currentDCOCharacter)
    }

    //UPDATE CONFLICTS
    suspend fun updateConflicts(dcoCharacter: Int, conflicts: Int){
        dcoCharacterDAO.updateConflicts(dcoCharacter, conflicts)
    }

    //GET CONFLICTS
    fun getConflicts(currentDCOCharacter: Int): LiveData<Int> {
        return dcoCharacterDAO.getConflict(currentDCOCharacter)
    }

    //UPDATE STORY
    suspend fun updateStory(dcoCharacter: Int, stories: String){
        dcoCharacterDAO.updateStories(dcoCharacter, stories)
    }

    //GET STORY
    fun getStories(currentDCOCharacter: Int): LiveData<String> {
        return dcoCharacterDAO.getStory(currentDCOCharacter)
    }

    //UPDATE REWARDS
    suspend fun updateRewards(dcoCharacter: Int, rewards: String){
        dcoCharacterDAO.updateRewards(dcoCharacter, rewards)
    }

    //GET REWARDS
    fun getRewards(currentDCOCharacter: Int): LiveData<String>{
        return dcoCharacterDAO.getRewards(currentDCOCharacter)
    }

    //UPDATE BACKGROUND
    suspend fun updateBackground(dcoCharacter: Int, backGround: String){
        dcoCharacterDAO.updateBackground(dcoCharacter, backGround)
    }

    //GET BACKGROUND
    fun getBackground(currentDCOCharacter: Int): LiveData<String> {
        return dcoCharacterDAO.getBackground(currentDCOCharacter)
    }

    //UPDATE LUCK
    suspend fun updateLuck(dcoCharacter: Int, luck: Int) {
        dcoCharacterDAO.updateLuck(dcoCharacter, luck)
    }

    //GET LUCK
    fun getLuck(currentDCOCharacter: Int): LiveData<Int> {
        return dcoCharacterDAO.getLuck(currentDCOCharacter)
    }

}