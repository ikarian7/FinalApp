package com.example.finalapp.ui.combat

import android.app.AlertDialog
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
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.model.WeaponItem
import com.example.finalapp.model.WeaponsAdapter

class CombatFragment : Fragment() {
    private lateinit var combatViewModel: ActivityViewModel

    private val weapons = arrayListOf<WeaponItem>()
    private val weaponsAdapter = WeaponsAdapter(weapons)

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

        initViews()
        initViewModel()
    }

    fun initViews() {
        rvWeapons.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvWeapons.adapter = weaponsAdapter
        rvWeapons.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        fab.setOnClickListener {
            addWeapon()
        }
    }

    fun initViewModel(){
        combatViewModel.weapons.observe(viewLifecycleOwner, Observer { weapons ->
            this.weapons.clear()
            this.weapons.addAll(weapons)
            weaponsAdapter.notifyDataSetChanged()
        })

        combatViewModel.currentChara.observe(viewLifecycleOwner, Observer { currentChara ->
            if(currentChara != null) {
                tvLuck.text = getString(R.string.Luck, currentChara.luck.toString())
                woundBar.rating = currentChara.wounds.toFloat()
                Log.d("wounds", woundBar.rating.toString())
                Log.d("wounds current", currentChara.wounds.toString())
            } else {
                tvLuck.text = getString(R.string.Luck, maxluck)
                woundBar.rating = 0.0f
            }
        })
        ibMin.setOnClickListener{adjustHealth("min")}
        ibPlus.setOnClickListener{adjustHealth("plus")}
        //Log.d("wounds when enter", woundBar.rating.toString())
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
                    combatViewModel.wounds = MutableLiveData(woundBar.rating.toInt())
                   // Log.d("wounds", woundBar.rating.toString())
                }
            })

            combatViewModel.currentCharaId.observe(viewLifecycleOwner, Observer { currentCharaId ->
                combatViewModel.updateAll(currentCharaId)

            })

            Toast.makeText(combatViewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveAll()
    }

    private fun makeAlert(layout: Int, title: Int): Pair<AlertDialog, View> {
        val dialogView = LayoutInflater.from(context).inflate(layout, null)
        val newAlertBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle(title)

        return Pair(newAlertBuilder.show(), dialogView)
    }

    private fun addWeapon() {
        val (alertDialog, alertView) = makeAlert(R.layout.add_weapon_dialogue, R.string.title_add)

        val exitButton: Button = alertDialog.findViewById(R.id.btnItemCancel)
        val addButton: Button = alertDialog.findViewById(R.id.btnItemAdd)


        val nameInputField = alertDialog.findViewById<EditText>(R.id.etItemName)
        val tohitInputField = alertDialog.findViewById<EditText>(R.id.etToHit)

        exitButton.setOnClickListener {
            alertDialog.dismiss()
        }

        addButton.setOnClickListener {
            if( nameInputField.text.toString() != "" &&
                tohitInputField.text.toString() != ""
            ) {
                val name = nameInputField.text.toString()
                val tohit = tohitInputField.text.toString().toInt()

                combatViewModel.currentCharaId.observe(viewLifecycleOwner, Observer {currentCharaId ->
                    if(currentCharaId != null) {
                        val newWeapon = WeaponItem(name, tohit, currentCharaId.toLong())
                        combatViewModel.addWeapon(newWeapon)
                    } else {
                        Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show()
                    }
                })


                alertDialog.dismiss()
            } else {
                //TODO: make string value
                Toast.makeText(context, "Vul iets in, alsjeblieft", Toast.LENGTH_LONG).show()
            }
        }

        alertDialog.show()
    }
}
