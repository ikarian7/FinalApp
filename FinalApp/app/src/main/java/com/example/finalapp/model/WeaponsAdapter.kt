package com.example.finalapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.R
import kotlinx.android.synthetic.main.weapons.view.*

class WeaponsAdapter(private val weaponItems: List<WeaponItem>) : RecyclerView.Adapter<WeaponsAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.weapons, parent, false)
        )
    }
    /* Returns the size of the list*/

    override fun getItemCount(): Int {
        return weaponItems.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weaponItems[position])
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(weaponItem: WeaponItem){
            itemView.tvName.text = weaponItem.name
            itemView.tvTohit.text = weaponItem.toHit.toString()
        }
    }
}
