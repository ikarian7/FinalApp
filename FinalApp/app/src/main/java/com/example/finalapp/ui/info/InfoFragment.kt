package com.example.finalapp.ui.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.R
import com.example.finalapp.ui.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {
    private lateinit var infoViewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        infoViewModel.currentChara.observe(viewLifecycleOwner, Observer { currentChara ->
            var cartelinfo = "info"
            if (currentChara != null) {
                if(currentChara.cartel == "The Circle")
                    cartelinfo = this.getString(R.string.CircleInfo)
                else{
                    cartelinfo = this.getString(R.string.FamilyInfo)
                }
                if(currentChara.profession == "Alchemist"){
                    tvProfSkills.text = this.getString(R.string.AlchemistSkills)
                }
                else{
                    tvProfSkills.text = this.getString(R.string.AssassinSkills)
                }
                if(currentChara.quirk == "A Home in Little Taona"){
                    tvQuirkInfo.text = this.getString(R.string.ToanaQuirk)
                }
                else{
                    tvQuirkInfo.text = this.getString(R.string.AnimalQuirk)
                }
             tvCartelInfo.text = cartelinfo
            }
            Log.d("cartelinfo", cartelinfo)
        })
    }

    override fun onPause() {
        super.onPause()

    }
}
