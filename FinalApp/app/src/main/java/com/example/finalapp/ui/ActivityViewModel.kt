package com.example.finalapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.finalapp.database.DCOCharacterRepository
import com.example.finalapp.model.DCOCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val stCharacterID = 1;

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val dcoRepo = DCOCharacterRepository(application.applicationContext)
    var currentCharaId = dcoRepo.getSelectedChara()
    val dcoCharacters: LiveData<List<DCOCharacter>> = dcoRepo.getAllCharacters()

    var currentChara = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getDCOCharacter(stCharacterID)
        } else {
            dcoRepo.getDCOCharacter(currentCharaId)
        }
    }

    var quirk = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getQuirk(stCharacterID)
        } else {
            dcoRepo.getQuirk(currentCharaId)
        }
    }

    fun getCharaByID(id: Int): LiveData<DCOCharacter?> {
        return dcoRepo.getDCOCharacter(id)
    }


    fun addDcoCharacter(newCharacter: DCOCharacter){
        ioScope.launch {
            dcoRepo.insertCharacter(newCharacter)
        }
    }

    fun changeSelectedChara(chosenCharacterID: Int, currentCharacterID: Int) {
        ioScope.launch {
            dcoRepo.changeSelectedChara(chosenCharacterID, currentCharacterID)
        }
    }

    fun deleteChara(dcoCharacter: DCOCharacter) {
        ioScope.launch {
            dcoRepo.deleteChara(dcoCharacter)
        }
    }

    fun updateQuirk(currentCharacterID: Int) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                dcoRepo.updateQuirk(currentCharacterID, quirk.value!!)
            }
        }
    }
}