package com.example.finalapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finalapp.R
import com.example.finalapp.ui.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.currentChara.observe(viewLifecycleOwner, Observer { currentChara ->
            if (currentChara != null) {
                tvCharaName.text = currentChara.name
                Log.d("name textview", tvCharaName.text as String)
                Log.d("name database", currentChara.name)
                tvCartelText.text = currentChara.cartel
                tvProfessionText.text = currentChara.profession
                etQuirkText.setText(currentChara.quirk)
                tvPlacesIncon.text = currentChara.inconPlaces
            }
        })

    }

    private fun saveQuirk() {
        Toast.makeText(viewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
        viewModel.currentChara.observe(viewLifecycleOwner, Observer {currentChara ->
            if(currentChara != null) {
                viewModel.quirk = MutableLiveData(etQuirkText.text.toString())
            }
        })

        viewModel.currentCharaId.observe(viewLifecycleOwner, Observer { currentCharaId ->
            viewModel.updateQuirk(currentCharaId)
        })

        Toast.makeText(viewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        saveQuirk()
    }
}