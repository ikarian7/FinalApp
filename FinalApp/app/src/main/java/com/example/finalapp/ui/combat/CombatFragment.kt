package com.example.finalapp.ui.combat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.R
import com.example.finalapp.ui.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_combat.*
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class CombatFragment : Fragment() {
    private lateinit var combatViewModel: ActivityViewModel
    var maxluck = "100"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        combatViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        return inflater.inflate(R.layout.fragment_combat, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    fun initViewModel(){
        combatViewModel.currentChara.observe(viewLifecycleOwner, Observer { currentChara ->
            if(currentChara != null) {
                tvLuck.text = getString(R.string.Luck, currentChara.luck.toString())
            } else {
                tvLuck.text = getString(R.string.Luck, maxluck)
            }
        })
        ibMin.setOnClickListener{adjustHealth("min")}
        ibPlus.setOnClickListener{adjustHealth("plus")}
    }

    fun adjustHealth(type: String){
        var luck = tvLuck.text.toString().replace("Luck: ", "")
        //Log.d("luck", luck)
        var filledHealth = etHealth.text.toString().toInt()

        var currentLuck = luck.toInt()

        if(type == "min"){
            currentLuck -= filledHealth
        }
        else{
            currentLuck += filledHealth
        }
        tvLuck.text = getString(R.string.Luck, currentLuck.toString())
    }

    private fun saveAll() {
            Toast.makeText(combatViewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
            combatViewModel.currentChara.observe(viewLifecycleOwner, Observer {currentChara ->
                if(currentChara != null) {
                    var currentHealth = tvLuck.text.toString().replace("Luck: ", "")
                    var luckGet = currentHealth.toInt()
                    combatViewModel.luck = MutableLiveData(luckGet)
                }
            })

            combatViewModel.currentCharaId.observe(viewLifecycleOwner, Observer { currentCharaId ->
                combatViewModel.updateLuck(currentCharaId)
            })

            Toast.makeText(combatViewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveAll()
    }
}
