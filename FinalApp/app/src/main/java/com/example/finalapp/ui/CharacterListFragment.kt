package com.example.finalapp.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.finalapp.R
import com.example.finalapp.model.CharacterAdapter
import com.example.finalapp.model.DCOCharacter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.characterlist.*

class CharacterListFragment : Fragment() {

    private val dcoCharacters = arrayListOf<DCOCharacter>()
    private val characterAdapter = CharacterAdapter(dcoCharacters, { dcoCharacter -> onCharacterClick(dcoCharacter) }, { dcoCharacter -> onDeleteClick(dcoCharacter)})
    private lateinit var viewModel: ActivityViewModel



    //This function handles the loading of another character
    private fun onCharacterClick(dcoCharacter: DCOCharacter) {
        viewModel.currentChara.observe(this, Observer {currentChara ->
            if (currentChara != null) {
                if(currentChara.id == dcoCharacter.id) {
                    Snackbar.make(rvCharacters, R.string.selected, Snackbar.LENGTH_SHORT).show()
                } else {
                    viewModel.changeSelectedChara(dcoCharacter.id!!.toInt(), currentChara.id!!.toInt())
                    //startHomeFragment()
                }
            } else {
                Snackbar.make(rvCharacters, R.string.error, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun onDeleteClick(dcoCharacter: DCOCharacter) {
        viewModel.currentChara.observe(this, Observer {currentChara ->
            if (currentChara != null) {
                if(currentChara.id == dcoCharacter.id) {
                    Snackbar.make(rvCharacters, "You can't delete your current character!!", Snackbar.LENGTH_SHORT).show()
                } else {
                    viewModel.getCharaByID(dcoCharacter.id?.toInt()!!).observe(this, Observer { deletableDCOCharacter ->
                        if (deletableDCOCharacter != null) {
                            viewModel.deleteChara(deletableDCOCharacter)
                            viewModel.deleteChara(deletableDCOCharacter)
                        }
                    })
                }
            } else {
                Snackbar.make(rvCharacters, R.string.error, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}