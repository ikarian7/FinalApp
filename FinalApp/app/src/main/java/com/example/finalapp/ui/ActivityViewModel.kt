package com.example.finalapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.finalapp.database.DCOCharacterRepository
import com.example.finalapp.model.DCOCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val stCharacterID = 1;

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val dcoRepo = DCOCharacterRepository(application.applicationContext)

    fun addDcoCharacter(newCharacter: DCOCharacter){
        ioScope.launch {
            dcoRepo.insertCharacter(newCharacter)
        }
    }


}