package com.example.finalapp.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.R
import com.example.finalapp.model.CharacterAdapter
import com.example.finalapp.model.DCOCharacter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.characterlist.*


class CharacterListFragment : Fragment() {

    private lateinit var parentActivity: Activity
    private val dcoCharacters = arrayListOf<DCOCharacter>()
    private val characterAdapter = CharacterAdapter(dcoCharacters, { dcoCharacter -> onCharacterClick(dcoCharacter) }, { dcoCharacter -> onDeleteClick(dcoCharacter)})
    private lateinit var viewModel: ActivityViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        return inflater.inflate(R.layout.characterlist, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        //Making the recyclerview for the list of all characters
        rvCharacters.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvCharacters.adapter = characterAdapter
        rvCharacters.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        parentActivity = requireActivity()

    }

    private fun observeViewModel() {
        viewModel.dcoCharacters.observe(viewLifecycleOwner, Observer { dndCharacters ->
            this.dcoCharacters.clear()
            this.dcoCharacters.addAll(dndCharacters)
            characterAdapter.notifyDataSetChanged()
        })
    }

    //This function handles the loading of another character
    private fun onCharacterClick(dcoCharacter: DCOCharacter) {
        viewModel.currentChara.observe(this, Observer {currentChara ->
            if (currentChara != null) {
                if(currentChara.id == dcoCharacter.id) {
                    Snackbar.make(rvCharacters, R.string.selected, Snackbar.LENGTH_SHORT).show()
                } else {
                    viewModel.changeSelectedChara(dcoCharacter.id!!.toInt(), currentChara.id!!.toInt())
                    startHome()
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
         private fun startHome() {
                parentActivity.findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_character)
            }
}