package com.example.findhim.persistency

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.GameDetailsActivity
import com.example.findhim.R
import com.example.findhim.databinding.LogElementLayoutBinding

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = LogElementLayoutBinding.bind(itemView)
    fun bind(game: Game) {
        binding.playerName.text = game.nickname
        binding.gameTime.text = game.gameTime
        binding.clicks.text = game.clicks
        binding.gameId.text = game.id.toString()
        binding.logElement.setOnClickListener { launchGetMoreInfo(game) }
    }

    private fun launchGetMoreInfo(game: Game) {
        val intent = Intent(itemView.context, GameDetailsActivity::class.java)
        intent.putExtra("game", game)
        itemView.context.startActivity(intent)
    }
    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.log_element_layout, parent, false)
            return GameViewHolder(view)
        }
    }
}