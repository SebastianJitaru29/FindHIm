package com.example.findhim.persistency.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R
import com.example.findhim.persistency.Game

class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
    val playerName = view.findViewById<TextView>(R.id.playerName)

    fun render(game: Game){
        playerName.text = game.nickname
    }
}