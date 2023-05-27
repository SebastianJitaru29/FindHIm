package com.example.findhim.persistency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R
import com.example.findhim.databinding.LogLayoutBinding

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val playerNameView: TextView = itemView.findViewById(R.id.playerName)
    private val gameTime: TextView = itemView.findViewById(R.id.gameTime)
    private val cliks:TextView = itemView.findViewById(R.id.clicks)
    private val id:TextView = itemView.findViewById(R.id.gameId)

    fun bind(game: Game) {
        playerNameView.text = game.nickname
        gameTime.text = game.gameTime
        cliks.text = game.clicks
        id.text = game.id.toString()
    }

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.log_element_layout, parent, false)
            return GameViewHolder(view)
        }
    }
}