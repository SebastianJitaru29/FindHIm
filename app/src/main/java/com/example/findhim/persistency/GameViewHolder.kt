package com.example.findhim.persistency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R
import com.example.findhim.databinding.LogElementLayoutBinding
import com.example.findhim.fragments.log.GameDetailsFragment

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = LogElementLayoutBinding.bind(itemView)
    fun bind(game: Game) {
        binding.playerName.text = game.nickname
        binding.gameTime.text = game.gameTime
        binding.clicks.text = game.clicks
        binding.gameId.text = game.id.toString()
        binding.currenTime.text = game.date
//        binding.logElement.setOnClickListener { launchGetMoreInfo(game) }
        binding.logElement.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val gameDetailsFragment = GameDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("game", game)
            gameDetailsFragment.arguments = bundle

            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(
                    R.id.detailsContainer,
                    gameDetailsFragment
                )
                .addToBackStack(null)
                .commit()
        }

    }

    private fun launchGetMoreInfo(game: Game) {
//        val intent = Intent(itemView.context, GameDetailsActivity::class.java)
//        intent.putExtra("game", game)
//        itemView.context.startActivity(intent)
//    val gameDetailsFragment = GameDetailsFragment()
//    val bundle = Bundle()
//    bundle.putParcelable("game", game)
//    gameDetailsFragment.arguments = bundle
//
//    val fragmentManager = itemView.context.supportFragmentManager
//    fragmentManager.beginTransaction()
//        .replace(
//            R.id.detailsContainer,
//            gameDetailsFragment
//        ) // Replace "R.id.container" with the ID of your container layout
//        .addToBackStack(null)
//        .commit()

    }

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.log_element_layout, parent, false)
            return GameViewHolder(view)
        }
    }
}