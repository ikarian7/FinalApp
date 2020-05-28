package com.example.finalapp.ui.combat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CombatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is combat Fragment"
    }
    val text: LiveData<String> = _text
}