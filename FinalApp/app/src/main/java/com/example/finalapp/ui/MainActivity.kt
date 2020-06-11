package com.example.finalapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalapp.R
import com.example.finalapp.model.DCOCharacter
import kotlinx.android.synthetic.main.add_char_dialogue.*
import kotlinx.android.synthetic.main.add_char_dialogue.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_character,
            R.id.navigation_info,
            R.id.navigation_combat,
            R.id.navigation_story
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.action_one) {
            showAlert()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Select character", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Delete character", Toast.LENGTH_LONG).show()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    private fun showAlert(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_char_dialogue, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Add Character")

        //show dialog
        val  mAlertDialog = mBuilder.show()

        val nameInputField = mAlertDialog.findViewById<EditText>(R.id.etName)
        val cartspinner: Spinner = mAlertDialog.findViewById(R.id.cartelspinner)
        val profspinner: Spinner = mAlertDialog.findViewById(R.id.professionspinner)
        val quispinner: Spinner = mAlertDialog.findViewById(R.id.quirkspinner)

        mDialogView.dialogAddBtn.setOnClickListener{

            Log.d("spinnertest", cartspinner.selectedItem.toString())


            val newCharacter: DCOCharacter = DCOCharacter(false, nameInputField.text.toString(), cartspinner.selectedItem.toString(),
                profspinner.selectedItem.toString(),
                quispinner.selectedItem.toString())
            mainViewModel.addDcoCharacter(newCharacter)

            mAlertDialog.dismiss()
        }
        mDialogView.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
}
