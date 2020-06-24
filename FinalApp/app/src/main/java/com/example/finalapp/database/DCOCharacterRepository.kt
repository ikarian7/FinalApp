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

    suspend fun updateQuirk(dcoCharacter: Int, quirk: String){
        dcoCharacterDAO.updateQuirk(dcoCharacter, quirk)
    }

    fun getQuirk(currentDCOCharacter: Int): LiveData<String> {
        return dcoCharacterDAO.getQuirk(currentDCOCharacter)
    }

    @Transaction
   suspend fun getStorypage(currentDCOCharacter: Int): Pair<Pair<Int, String>, Pair<String, String>>{
        return Pair(
            Pair(dcoCharacterDAO.getConflict(currentDCOCharacter), dcoCharacterDAO.getStory(currentDCOCharacter)),
            Pair(dcoCharacterDAO.getRewards(currentDCOCharacter), dcoCharacterDAO.getBackground(currentDCOCharacter))
        )
    }
}