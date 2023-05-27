package com.example.findhim.persistency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R
import com.example.findhim.databinding.LogElementLayoutBinding
import com.example.findhim.databinding.LogLayoutBinding

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = LogElementLayoutBinding.bind(itemView)
    fun bind(game: Game) {
        binding.playerName.text = game.nickname
        binding.gameTime.text = game.gameTime
        binding.clicks.text = game.clicks
        binding.gameId.text = game.id.toString()
    }

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.log_element_layout, parent, false)
            return GameViewHolder(view)
        }
    }
}