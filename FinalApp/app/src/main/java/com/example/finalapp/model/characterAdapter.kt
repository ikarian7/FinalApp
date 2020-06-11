package com.example.finalapp.model

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.R
import kotlinx.android.synthetic.main.item.view.*

class CharacterAdapter(private val dcoCharacters: List<DCOCharacter>,
                       private val onSelect: (DCOCharacter) -> Unit,
                       private val onDelete: (DCOCharacter) -> Unit) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        )
    }
    /* Returns the size of the list*/

    override fun getItemCount(): Int {
        return dcoCharacters.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dcoCharacters[position])
    }




    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(dcoCharacter: DCOCharacter){
            itemView.tvCharacterName.text = dcoCharacter.name
            itemView.tvCartelName.text = dcoCharacter.cartel
            itemView.tvProfName.text = dcoCharacter.profession
        }
        init {
            itemView.findViewById<ConstraintLayout>(R.id.clSub).setOnClickListener { onSelect(dcoCharacters[adapterPosition]) }
            itemView.findViewById<ImageButton>(R.id.ibDelete).setOnClickListener { onDelete(dcoCharacters[adapterPosition]) }
        }
    }
}