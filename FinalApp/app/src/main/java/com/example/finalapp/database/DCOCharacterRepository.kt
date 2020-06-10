package com.example.finalapp.database

import android.content.Context
import androidx.lifecycle.LiveData
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
}