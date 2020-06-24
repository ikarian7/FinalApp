package com.example.finalapp.ui.story

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_story.*

class StoryFragment : Fragment() {
    private lateinit var storyViewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storyViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel(){
        storyViewModel.currentChara.observe(viewLifecycleOwner, Observer { currentChara ->
        if (currentChara != null) {
            etStory.setText(currentChara.story)
            etBackground.setText(currentChara.background)
            etRewards.setText(currentChara.rewards)
            storybar.rating = currentChara.conflictbar.toFloat()

             }
        })
    }

    private fun updateStoryPage(){
        Toast.makeText(storyViewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
        storyViewModel.currentChara.observe(viewLifecycleOwner, Observer {currentChara ->
            if(currentChara != null) {
                storyViewModel.backGround = MutableLiveData(etBackground.text.toString())
                storyViewModel.rewards = MutableLiveData(etRewards.text.toString())
                storyViewModel.stories = MutableLiveData(etStory.text.toString())
                storyViewModel.conflicts = MutableLiveData(storybar.rating.toInt())
            }
        })

        storyViewModel.currentCharaId.observe(viewLifecycleOwner, Observer { currentCharaId ->
            storyViewModel.updateStory(currentCharaId)
        })

        Toast.makeText(storyViewModel.getApplication(), R.string.editedText, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        updateStoryPage()
    }
}