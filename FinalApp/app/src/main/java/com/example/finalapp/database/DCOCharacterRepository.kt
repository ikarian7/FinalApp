package com.example.finalapp.database

import android.content.Context
import androidx.lifecycle.LiveData

class DnDCharacterRepository(context: Context) {
    private val dcoCharacterDAO: DCOCharacterDAO

    init {
        val database = DnDDatabase.getDatabase((context))
        dcoCharacterDAO = database!!.dcoCharacterDAO()
    }
}