package com.example.finalapp.ui.combat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.finalapp.R

class CombatFragment : Fragment() {

    private lateinit var combatViewModel: CombatViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        combatViewModel =
                ViewModelProviders.of(this).get(CombatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_combat, container, false)
        //val textView: TextView = root.findViewById(R.id.text_notifications)
        combatViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        })
        return root
    }
}
