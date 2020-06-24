package com.example.finalapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.finalapp.database.DCOCharacterRepository
import com.example.finalapp.model.DCOCharacter
import com.example.finalapp.model.WeaponItem
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

    var luck = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getLuck(stCharacterID)
        } else {
            dcoRepo.getLuck(currentCharaId)
        }
    }

    var conflicts = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getConflicts(stCharacterID)
        } else {
            dcoRepo.getConflicts(currentCharaId)
        }
    }

    var stories = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getStories(stCharacterID)
        } else {
            dcoRepo.getStories(currentCharaId)
        }
    }

    var rewards = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getRewards(stCharacterID)
        } else {
            dcoRepo.getRewards(currentCharaId)
        }
    }

    var backGround = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getBackground(stCharacterID)
        } else {
            dcoRepo.getBackground(currentCharaId)
        }
    }

    var wounds = Transformations.switchMap(currentCharaId){currentCharaId ->
        if(currentCharaId == null){
            dcoRepo.getWounds(stCharacterID)
        } else {
            dcoRepo.getWounds(currentCharaId)
        }
    }

    var weapons = Transformations.switchMap(currentCharaId) {currentCharaId ->
        if(currentCharaId == null) {
            dcoRepo.getWeapons(stCharacterID)
        } else {
            dcoRepo.getWeapons(currentCharaId)
        }
    }

    fun addWeapon(weapon: WeaponItem) {
        ioScope.launch {
            dcoRepo.insertWeapon(weapon)
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

    fun updateAll(currentCharacterID: Int) {
        mainScope.launch {
            withContext(Dispatchers.IO){
                dcoRepo.updateWounds(currentCharacterID, wounds.value!!)
                dcoRepo.updateLuck(currentCharacterID, luck.value!!)
            }
        }
    }

    fun updateStory(currentCharacterID: Int){
        mainScope.launch {
            withContext(Dispatchers.IO){
                dcoRepo.updateBackground(currentCharacterID, backGround.value!!)
                dcoRepo.updateStory(currentCharacterID, stories.value!!)
                dcoRepo.updateRewards(currentCharacterID, rewards.value!!)
                dcoRepo.updateConflicts(currentCharacterID, conflicts.value!!)
            }
        }
    }
}