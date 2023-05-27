package com.example.findhim.persistency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val playerNameView: TextView = itemView.findViewById(R.id.playerName)

    fun bind(text: String?) {
        playerNameView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.log_element_layout, parent, false)
            return GameViewHolder(view)
        }
    }
}